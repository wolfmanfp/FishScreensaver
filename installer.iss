[Setup]
AppName=Fish Screensaver
AppVersion=1.0
DefaultDirName={win}\FishScr
SetupIconFile=screensaver.ico
OutputBaseFilename=FishScrSetup
OutputDir=target
Compression=lzma2
SolidCompression=yes

[Files]
Source: "target\FishScreensaver.scr"; DestDir: "{app}\..\"

[Registry]
Root: HKCU; Subkey: "Software\FishScreensaver"; ValueName: "NumberOfFish"; ValueType: dword; ValueData: "5"; Flags: uninsdeletekey