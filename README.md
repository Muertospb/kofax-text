# Kofax-text
The program performs text output in accordance with the specification "Specification for trial task Version 2, revision 3" from Kofax Inc.

Min JRE version is 1.8

If the program will be launched as jar artifact from the Windows command line and text file contains cyrillic symbols 
then property (-Dfile.encoding=UTF-8) must be set.

Example: "java -jar -Dfile.encoding=UTF-8 kofax-text-viewer-0.1.0.jar"

Using the application.properties file it is possible to set:
- any number of font sizes (comma-separated), default is 9,12;
- number of words of the same font size in a row, default is 2.