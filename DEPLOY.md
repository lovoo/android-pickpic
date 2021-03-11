# Internal deploy manual

## Description
PickPic is an external library that should be tested before it final released to the public.

Here some best practises
* test your changes with the example app
* make your changes accessible in LOVOO see `local deploy`
* deploy your changes as BETA see `beta deploy`
* release your changes see `release deploy`

## Local deploy (not working right now)
* Changes in PickPic `<root>/build.gradle`
    * change version
```
    ext.version_name = version == null ? "1.3.0-local" : version
    ext.version_code = code == null ? 30 : code.toInteger()
```
    * sync project and run `gradle publishToMavenLocal`
* Changes in LOVOO `dependencies.gradle`
    * enable maven local
    ```
        lovoo.repositories = {
            mavenLocal() // enable when testing with locally deployed artifacts
            maven { // google()
                url "https://maven.google.com"
                content = excludedGroupsContent
            }
            ...
    ```
    * select new version `pickpic_version = "1.3.0-local"`
    * sync and build LOVOO

## Beta deploy
* open android-pickpic in bitrise
* run new task
    * switch tab to advanced
    * select the branch to deploy example: `develop`
    * add variable `VERSION` with value `<VERSION>-BETA-<DATE>` example: `1.3.0-BETA-2020-10-28-14-00`
* update LOVOO dependency to this version
    * test it with your own LOVOO debug build
    * put your ticket to QA

## Release deploy
When a BETA release is working and no issues appear
* create PR from develop to master
    * add a label to determine witch kind of deploy
* Update LOVOO dependency to the latest version (optional)

Bitrise will then run all the steps to deploy the master if the build was successful.