name := "Timeslot-root"

organization := "com.timeslot"

organizationName := "Timeslot Inc."

organizationHomepage := Some(new URL("http://timeslot.com"))

// so we can use keywords from Android, such as 'Android' and 'proguardOptions'
import android.Keys._
import android.Dependencies.{aar,apklib}


// pick the version of scala you want to use
scalaVersion := "2.11.7"

// scala 2.10 flag for feature warnings
scalacOptions in Compile += "-feature"

// for non-ant-based projects, you'll need this for the specific build target:
platformTarget in Android := "android-23"


// LIBS ---
// External library dependecies
resolvers += "Mandubian repository snapshots" at "https://github.com/mandubian/mandubian-mvn/raw/master/snapshots/"

libraryDependencies ++= Seq(
  "org.macroid" %% "macroid" % "2.0.0-M2",
  "com.google.code.gson" % "gson" % "2.2.4"
)

addCompilerPlugin("org.brianmckenna" %% "wartremover" % "0.10")

scalacOptions in (Compile, compile) ++= Seq(
  "-P:wartremover:cp:" + (dependencyClasspath in Compile).value
    .files.map(_.toURL.toString)
    .find(_.contains("org.macroid/macroid_")).get,
  "-P:wartremover:traverser:macroid.warts.CheckUi"
)

/////////// PROGUARD

val proguardSettings = Seq (
  useProguard in Android := true
)

proguardCache in Android ++= Seq(
  "macroid","gson"
)

///////////// DEX

dexMaxHeap in Android := "2g"
dexMulti in Android := true


////////////

// call install and run without having to prefix with android:
run <<= run in Android

install <<= install in Android