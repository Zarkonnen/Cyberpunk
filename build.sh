#! /bin/sh

# Go into our own dir.
cd "`dirname "$0"`"

# Linux version
echo "Building Linux version..."
cp -r release/linux-base release/meathook-$1-linux
cp dist/Cyberpunk.jar release/meathook-$1-linux/
cp dist/lib/* release/meathook-$1-linux/lib/
cp -r lib/native/linux release/meathook-$1-linux/lib/native/
echo "Done."

# Windows version
echo "Building Windows version..."
/Applications/launch4j/launch4j release/win_launch4j_cfg.xml
cp -r release/win-base release/meathook-$1-win
mv release/Cyberpunk.exe release/meathook-$1-win/
cp dist/lib/* release/meathook-$1-win/lib/
cp -r lib/native/windows release/meathook-$1-win/lib/native/
echo "Done."

# Mac version
echo "Building Mac version..."
cp -r release/mac-base release/meathook-$1-mac
cp dist/Cyberpunk.jar release/meathook-$1-mac/Cyberpunk.app/Contents/Resources/Java/
cp dist/lib/* release/meathook-$1-mac/Cyberpunk.app/Contents/Resources/Java/
cp -r lib/native/macosx release/meathook-$1-mac/Cyberpunk.app/Contents/Resources/Java/native/
codesign -s "Developer ID Application: David Stark" -f release/meathook-$1-mac/Cyberpunk.app
echo "Done."

# Zipping
echo "Zipping Linux version"
zip -r release/meathook-$1-linux.zip release/meathook-$1-linux
echo "Zipping Windows version"
zip -r release/meathook-$1-win.zip release/meathook-$1-win
echo "Zipping Mac version"
zip -r release/meathook-$1-mac.zip release/meathook-$1-mac