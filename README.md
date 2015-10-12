
# Scala in Android with SBT using IDEA 14

This document assume to work under MacOsx 10.10.0:

To run correctly require:
 - Java 1.7
 - Android 6.0 (API 23) SDK
 - SBT 0.13+
 - android-sdk-plug in for SBT 1.5.4+
 - Scala 2.11+
 - IntelliJ IDEA 14
 - Proguard 5.2


## Steps:
### Install SBT in $HOME
Macports `$ port install sbt`
Homebrew `$ brew install sbt`

Other OS: `http://www.scala-sbt.org/release/tutorial/Setup.html`

### Install android-sdk-plugin (necessary to build with SBT, Android projects)

We will set the android SBT Plugins globally and not in the project folder:
select folder:
`$ cd $HOME/.sbt/0.13/`

create plugin directory:
`$ mkdir plugins`

create txt file named android.sbt in the plugins directory:
`$ cd plugins`
`$ nano android.sbt`

cut and paste this code in android.sbt:

`addSbtPlugin("com.hanhuy.sbt" % "android-sdk-plugin" % "1.5.4")
libraryDependencies += "net.sf.proguard" % "proguard-base" % "5.1"
resolvers += "Sonatype snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"
addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "1.7.0-SNAPSHOT")`

exit nano and save the file

### Download and unzip Android SDK:

`https://developer.android.com/sdk/index.html`

### Set $ANDROID_HOME environment value:
edit file in $HOME:
`$ nano .bash_profile`

create env var in the profile file:
`export ANDROID_HOME=$HOME/path-to-your-android-sdk-folder/android-sdk-macosx
export PATH=${PATH}:$ANDROID_HOME/tools:$ANDROID_HOME/platform-tools`

exit nano and save the file

### Install Idea 14 Community:
`https://www.jetbrains.com/idea/download/`

install Idea's plugins:
click: preferences/plugins/browse repositories

named:
- Android Scala
- SBT
- Scala

### Open Project
Run and open the project in IntellyJ IDEA 14 community edition

### Build Project
in the IDEA's Terminal/Shell prompt type:

`$ sbt clean`
clean the project and erase cached files

`$ sbt "gen-android target-name package-name starting-class"`
android-sdk-plugin instruction to prepare the android project (maybe not needed)

`$ sbt android:package`
generate an unsined APK

### Make it run
is better to run the project with IDE run button
instead of `$ sbt android:run`
there are already setted 2 configurations and the generated apk as to be signed as debug to run in the emulator

## SBT Config files:

- build.sbt: all the project sbt configuration
- proguard-sbt.txt: configuration file for proguard
- project.properties: target definition (target name definition is also available in build.sbt)
- local.properties: file for android-sdk-plugin to singed the generated apk

- project/android.sbt: local project plugins for SBT
- project/proguard.sbt: project related proguard dependency

### Understanding Scala:
http://danielwestheide.com/blog/2012/11/28/the-neophytes-guide-to-scala-part-2-extracting-sequences.html

