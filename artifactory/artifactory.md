
# Artifactory

Simple Docker Installation: https://www.jfrog.com/confluence/display/JFROG/Installing+Artifactory#InstallingArtifactory-DockerInstallation 
 1. make sure you are inside the artifactory folder
 2. `mkdir -p ./var/etc`
 3. `touch ./var/etc/system.yaml`
 4. `chown -R 1030:1030 ./var`
 5. `chmod -R 777 ./var`

Artifactory has uses 2 ports:
    - `8081`: endpoint for deploying artifacts
    - `8082`: ui endpoint

## Setup:
 1. Start Artifactory: `docker run --name artifactory -v ~/develop/personal/learning-and-notes/artifactory/var:/var/opt/jfrog/artifactory -d -p 8081:8081 -p 8082:8082 releases-docker.jfrog.io/jfrog/artifactory-oss:latest`
 2. `http://localhost:8082` -> Default username: `admin`, Default password: `password`
 3. change your admin password (system forces you to do so)
 4. create 2 `local repository`s -> Maven
 5. in settings one should have `Handle Snapshots` checked and the other `Handle Releases`
 6. (Optional) You can also set `Max Unique Snapshots` for your snapshot repo, so it won't keep many unused artifacts
 7. create a new user `ci-user` with a password `Password1`

## Deploy Locally
Deploy a local maven artifact to the local artifactory:
 1. cd into a maven project folder
 2. Add the `distributionManagement` snippet shown below to the pom
 3. run: `mvn -s ~/develop/personal/learning-and-notes/artifactory/settings.xml clean deploy --fail-at-end --no-transfer-progress`

This snippet should be pasted to the `pom.xml`:
```xml
	<distributionManagement>
        <!-- 400 Bad Request will be returned if you attempt to:
            Deploy a snapshot artifact (version ending in -SNAPSHOT) to a release repository
            Deploy a release artifact (version not ending in -SNAPSHOT) to a snapshot repository
        -->
		<repository>
			<id>artifactory-maven-release</id>
			<url>http://localhost:8081/artifactory/artifactory-maven-release</url>
		</repository>
		<snapshotRepository>
			<id>artifactory-maven-snapshot</id>
			<url>http://localhost:8081/artifactory/artifactory-maven-snapshot</url>
		</snapshotRepository>
	</distributionManagement>
```

### Best Practices For Artifactory Repositories
https://jfrog.com/whitepaper/best-practices-structuring-naming-artifactory-repositories/

TODO best practices for naming repositories

TODO best practices for permissions/groups

## Snapshot vs Release Repositories
Release repositories hold releases and Snapshot repositories hold snapshots (wow).
 * A **snapshot** is defined as an artifact with a version ending in `-SNAPSHOT`, for example having `<version>1.0-SNAPSHOT</version>` in your pom will result in an artfact like `hello-app-1.0-SNAPSHOT.jar`. Snapshots is the special version that indicates current deployment copy and not a regular, specific version.
 * A **release** artifact is an artifact which does not end in `-SNAPSHOT`.
`<version>1.0</version>` will create `hello-app-1.0.jar`

> You can write anything as a version, it does not need to be a number like 1.0.4 (this way of versioning your artifacts is called semantic versioning). For example, you can also version your artifacts like `PROD-20220408_1402`, `TEST-20220408_1402` and so on.

## How Artifactory Handles Artifacts

In Artifactory, a local repository can be a `Snapshot Repository` and also a `Release Repository` at the same time, if you chose so.
> But it is good practice to have 2 different repositories for each.

Your deployment might fail if you try to:
 - Deploy a snapshot artifact (version ending in -SNAPSHOT) to a release repository
 - Deploy a release artifact (version not ending in -SNAPSHOT) to a snapshot repository

> There can be multiple versions (with different timestamps) of a snapshot version in a snapshot repository but only one release version.

As an example, if you deploy a snapshot, artifactory will automatically add a timestamp to the files. If you pushed multiple snapshot artifacts with the version `1.0-SNAPSHOT`, they will land in the same `com/example/hello-app/1.0-SNAPSHOT` "folder" with following names (as an example):
 * notion-backup-1.0-20220408.094702-1.jar
 * notion-backup-1.0-20220408.094902-1.pom
 * notion-backup-1.0-20220408.094902-2.jar
 * notion-backup-1.0-20220408.094702-2.pom
 * notion-backup-1.0-20220408.095122-3.jar
 * notion-backup-1.0-20220408.095122-3.pom

And when you pull the `1.0-SNAPSHOT` artifact from that snapshot repository, you will get the latest version.

## Snapshot Cleanup

In a snaphot repository, you can set the `Max Unique Snapshots` setting.
Every time you deploy a snapshot, Artifactory will check the value `Max Unique Snapshots` for the repository, and if exceeded will mark any excess old snapshots for deletion. Then, **every 5 minutes**, Artifactory runs a background process that deletes those oldest snapshots that have been marked. For example, if you set Max Unique Snapshots to 5 and deploy a sixth and seventh snapshot to the repository, then next time the background process runs, it will delete the two oldest snapshots.

## Deploying Release Artifacts
Unlike snapshot artifacts, there is always only one version of a release artifact version, if you push a version, for example `1.0`, the jar will go to the `com/example/hello-app/1.0` folder and there will only be one jar in there.

If you push the same version again, it will give you a success response but **it does not override the files, if the same version is already present**



`TODO` pulling release artifacts, if you don't give a version, it pulls the latest?




TODO, play with Maven Settings:



* `--no-transfer-progress`: TODO




Check version and other infos (needs admin account): http://localhost:8081/artifactory/api/system/version