#!/bin/sh

echo "JAVA_HOME= ${JAVA_HOME}"
echo "ANDROID_SDK_HOME= ${ANDROID_SDK_HOME}"
echo "ANDROID_HOME= ${ANDROID_HOME}"
echo "ANDROID_NDK_HOME= ${ANDROID_NDK_HOME}"

unset ANDROID_NDK_HOME
echo "Gradle version:"
./gradlew -version

echo "Java version:"
java -version

mkdir "${ANDROID_HOME}/licenses" || true
echo "8933bad161af4178b1185d1a37fbf41ea5269c55" > $ANDROID_HOME/licenses/android-sdk-license
echo "d56f5187479451eabf01fb78af6dfcb131a6481e" > $ANDROID_HOME/licenses/android-sdk-license

git log --pretty=format:'+ %s' -n 14 | grep -v "Merge\|release\|Update circle\|Update build" > change.log

chmod +x gradlew
./gradlew  assembleInternalRelease
./gradlew  crashlyticsUploadDistributionInternalRelease

