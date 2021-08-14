
### TODO

@EnableCaching

@Cacheable(value = "DownloadCache", key = "{#relativeFileUrlWithPrefix, #secondArgument}")

## Caching
spring.cache.type=jcache
spring.cache.jcache.provider=org.ehcache.jsr107.EhcacheCachingProvider
spring.cache.jcache.config=classpath:ehcache.xml

# Disable Caching for local development / tests
#spring.cache.type=none

```xml
    <config xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xmlns='http://www.ehcache.org/v3'
        xsi:schemaLocation="http://www.ehcache.org/v3 http://www.ehcache.org/schema/ehcache-core.xsd">

        <cache alias="DownloadCache">
            <expiry>
                <ttl unit="hours">1</ttl>
            </expiry>

            <resources>
                <heap unit="entries">200</heap>
            </resources>
        </cache>

    </config>
```
