echo off

mkdir D:\luntbuild\Release\FileFormats
mkdir D:\luntbuild\Release\FileFormats\files_files

copy *.html D:\luntbuild\Release\FileFormats

copy files_files\*.css D:\luntbuild\Release\FileFormats\files_files
copy files_files\*.gif D:\luntbuild\Release\FileFormats\files_files
copy files_files\*.js D:\luntbuild\Release\FileFormats\files_files

goto :EOF
:exit

