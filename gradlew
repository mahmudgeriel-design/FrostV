#!/bin/sh
APP_NAME="Gradle"
APP_BASE_NAME=`basename "$0"`

warn() { echo "$*"; }
die() { echo; echo "$*"; echo; exit 1; }

PRG="$0"
while [ -h "$PRG" ]; do
  ls=`ls -ld "$PRG"`
  link=`expr "$ls" : '.*-> \(.*\)$'`
  if expr "$link" : '/.*' > /dev/null; then PRG="$link"
  else PRG=`dirname "$PRG"`/"$link"; fi
done
APP_HOME=`dirname "$PRG"`
APP_HOME=`cd "$APP_HOME" && pwd`
CLASSPATH="$APP_HOME/gradle/wrapper/gradle-wrapper.jar"
JAVACMD="${JAVA_HOME:+$JAVA_HOME/bin/}java"

exec "$JAVACMD" \
  -Xmx4g \
  -Xms512m \
  $JAVA_OPTS \
  $GRADLE_OPTS \
  "-Dorg.gradle.appname=$APP_BASE_NAME" \
  -classpath "$CLASSPATH" \
  org.gradle.wrapper.GradleWrapperMain "$@"
