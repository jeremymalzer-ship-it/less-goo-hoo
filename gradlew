#!/bin/bash
set -e
if [ -z "$GRADLE_USER_HOME" ]; then
  GRADLE_USER_HOME="$HOME/.gradle"
fi
export GRADLE_USER_HOME
GRADLE_VERSION="7.6"
GRADLE_URL="https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip"
GRADLE_HOME="$GRADLE_USER_HOME/gradle-${GRADLE_VERSION}"
if [ ! -d "$GRADLE_HOME" ]; then
  mkdir -p "$GRADLE_USER_HOME"
  echo "Downloading Gradle $GRADLE_VERSION..."
  cd "$GRADLE_USER_HOME"
  curl -L -o gradle-${GRADLE_VERSION}-bin.zip "$GRADLE_URL"
  unzip -q gradle-${GRADLE_VERSION}-bin.zip
  rm gradle-${GRADLE_VERSION}-bin.zip
  cd -
fi
"$GRADLE_HOME/bin/gradle" "$@"
