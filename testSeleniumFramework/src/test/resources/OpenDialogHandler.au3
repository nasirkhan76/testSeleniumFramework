
'If $CmdLine[0]<1 Then Exit EndIf

WinWaitActive("File Upload","","10")

If WinExists("Choose File to Upload") Then
ControlSetText("Choose File to Upload","","Edit1",$CmdLine[1])
ControlClick("Choose File to Upload","","&Open")
EndIf

If WinExists("File Upload") Then
ControlSetText("File Upload","","Edit1",$CmdLine[1])
ControlClick("File Upload","","&Open")
EndIf
