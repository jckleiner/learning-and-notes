
// https://blog.pavelsklenar.com/jenkins-creating-dynamic-project-folders-with-job-dsl/
def folderName = "PROJECT_NAME".toUpperCase()
def adminGroup = "ROLE_${folderName}_ADMIN"
def opsGroup = "ROLE_${folderName}_READER"
folder(folderName) {
    displayName(folderName)
  description("Folder for project 'PROJECT_NAME' generated by 'JOB_NAME'")
    authorization {
        permissions(opsGroup, [
            'hudson.model.Item.Read',
            'hudson.model.View.Read'
        ])
        permissionAll(adminGroup) 
    }
}

listView('DEV') {
    description('DEV Jobs')
    // filterBuildQueue()
    // filterExecutors()
    jobs {
        // name('release-projectA')
        regex(/job-.*/)
    }
    // jobFilters {
    //     status {
    //         status(Status.UNSTABLE)
    //     }
    // }
    columns {
        status()
        weather()
        name()
        lastSuccess()
        lastFailure()
        lastDuration()
        buildButton()
    }
}

// pipelineJob('job-name') {
//   definition {
//     cps {
//       script(readFileFromWorkspace('./logic/job1.jenkinsfile'))
//       sandbox()     
//     }
//   }
// }

/*
// Inline alternative
*/
pipelineJob('example-job-node') {
  definition {
    cps {
      script('''
        pipeline {
            agent {
                dockerfile {
                    dir '/var/jenkins_home/dockerfiles'
                    filename 'Dockerfile.node16'
                }
            }
            stages {
                stage('Test') {
                    steps {
                        sh 'node --version'
                        sh 'git --version'
                        sh 'curl --version'
                        sh 'jq --version'
                    }
                }
            }
        }
      '''.stripIndent())
      sandbox()     
    }
  }
}

pipelineJob('example-job-ubuntu22') {
  definition {
    cps {
      script('''
        // Host key verification failed: https://stackoverflow.com/questions/15214977/cloning-git-repo-causes-error-host-key-verification-failed-fatal-the-remote/29380672#29380672
        def repoUrl = 'ssh://git@github.com:jckleiner/notion-backup.git'
        // def repoUrl = 'https://github.com/jckleiner/notion-backup.git'
        def branch = 'master'

        pipeline {
            agent {
                dockerfile {
                    dir '/var/jenkins_home/dockerfiles'
                    filename 'Dockerfile.ubuntu22'
                }
            }
            stages {
                stage('Test') {
                    steps {
                        sh 'java --version'
                        sh 'git --version'
                        sh 'curl --version'
                        sh 'jq --version'
                    }
                }
                stage('Checkout') {
                    steps {
                        dir('my-repo') {
                            git branch: branch,
                                url: repoUrl
                        }
                    }
                }
                stage('Build') {
                    steps {
                        dir('my-repo') {
                            sh "mvn clean install --fail-at-end --no-transfer-progress --batch-mode"
                            
                        }
                    }
                }
            }
        }
      '''.stripIndent())
      sandbox()     
    }
  }
}

pipelineJob('github-demo') {
    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        github('jckleiner/learning-and-notes')
                    }
                }
            }
            scriptPath('jenkins/jcasc-2/logic/job1.jenkinsfile')
        }
    }
}