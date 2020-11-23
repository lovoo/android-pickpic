# Internal deploy manual

## Description
PickPic is an external library that should be tested before it final released to the public.

Here some best practises
* test your changes with the example app
* make your changes accessible in LOVOO `local deploy`
* deploy your changes as BETA `beta deploy`
* release your changes `release deploy`

## Local deploy


## Beta deploy
* open android-pickpic in bitrise
* run new task
    * switch tab to advanced
    * select the branch to deploy example: `develop`
    * add Variable `version` with value `<VERSION>-BETA-<DATE>` example: `1.3.0-BETA-2020-10-28-14-00`
* update LOVOO dependency to this version
    * test it with your own LOVOO debug build
    * put your ticket to QA

## Release deploy
When a BETA release is working and no issues appear
* create PR from develop to master
    * add a label to determine with kind of deploy
* Update LOVOO dependency to the latest version (optional)

Bitrise will then run all the steps to deploy the master if the build was successful.