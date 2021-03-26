# Internal deploy manual

## Description
PickPic is an external library that should be tested before it final released to the public.

Here some best practises
* test your changes with the example app
* make your changes accessible in LOVOO see `SNAPSHOT deploy`
* release your changes see `RELEASE deploy`

## SNAPSHOT deploy
### With Gradle
* Changes in PickPic `<root>/build.gradle`
    * change version so that it ends with SNAPSHOT
```
    ext.version_name = version == null ? "1.3.0-SNAPSHOT" : version
    ext.version_code = code == null ? 30 : code.toInteger()
```
    * sync project and run `gradle artifactoryPublish`
* Update dependencies in LOVOO `LovooDepVersion.kt`
* Test the LOVOO PickPic integration

### With Bitrise
* open android-pickpic in bitrise
* run new task
    * switch tab to advanced
    * select the branch to deploy example: `develop`
    * add variable `VERSION` with value `1.3.0-SNAPSHOT`
* Update dependencies in LOVOO `LovooDepVersion.kt`
* Test the LOVOO PickPic integration

## RELEASE deploy
### With GitHub PR
* create a PullRequest from develop to master
* add a label to the PR
    * bug_deploy ... will increase the last version number 1.1.1 -> 1.1.2
    * feature_deploy ... will increase the middle version number 1.1.1 -> 1.2.0
    * major_deploy ... will increase the first version number 1.1.1 -> 2.0.0
    * skip_deploy ... will not deploy a new version
* after merge Bitrise will
    * fetch all tags and sort them to take the latest version
    * it excludes SNAPSHOT/BETA/LEGACY tags from that
    * on that version it applies the configured change from the PR label
    * a corresponding GitHub tag will be created
    * a Slack notification will be triggered
    
## Additional Hints
* SNAPSHOT or RELEASE deploy depends only on the version number suffix
    * this means that Gradle and Bitrise can execute both deployments if needed (just define the ext.version_name or the `VERSION` as you need)
* `deploy` workflow in Bitrise will only be triggered by push to master
* `slack notification` will always be triggered by creating a new tag on GitHub
