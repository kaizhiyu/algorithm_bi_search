package com.algorithm.$_java_tools.power.Designer;

import com.algorithm.$8_annotation.Doc4Desc;

/**
 * //http://blog.csdn.net/shandalue/article/details/39031563
 * powerDesigner显示中文注释字段
 *
 * @author:v_fanhaibo on 2018/1/10.
 * @version:v1.0
 */
@Doc4Desc("power designer 显示中文名字")
public class PowerDesigner_desc {

    //FIR
    /**
     * [html] view plain copy
     代码一:将Name中的字符COPY至Comment中
     '******************************************************************************
     '* File:     name2comment.vbs
     '* Purpose:   Database generation cannot use object names anymore
     '         in version 7 and above.
     '         It always uses the object codes.
     '
     '         In case the object codes are not aligned with your
     '         object names in your model, this script will copy
     '         the object Name onto the object Comment for
     '         the Tables and Columns.
     '
     '* Title:
     '* Version:   1.0
     '* Company:   Sybase Inc.
     '******************************************************************************
     Option Explicit
     ValidationMode = True
     InteractiveMode = im_Batch

     Dim mdl ' the current model

     ' get the current active model
     Set mdl = ActiveModel
     If (mdl Is Nothing) Then
     MsgBox "There is no current Model "
     ElseIf Not mdl.IsKindOf(PdPDM.cls_Model) Then
     MsgBox "The current model is not an Physical Data model. "
     Else
     ProcessFolder mdl
     End If

     ' This routine copy name into comment for each table, each column and each view
     ' of the current folder
     Private sub ProcessFolder(folder)
     Dim Tab 'running   table
     for each Tab in folder.tables
     if not tab.isShortcut then
     tab.comment = tab.name
     Dim col ' running column
     for each col in tab.columns
     col.comment= col.name
     next
     end if
     next

     Dim view 'running view
     for each view in folder.Views
     if not view.isShortcut then
     view.comment = view.name
     end if
     next

     ' go into the sub-packages
     Dim f ' running folder
     For Each f In folder.Packages
     if not f.IsShortcut then
     ProcessFolder f
     end if
     Next
     end sub
     */



    //SEC
    /**
     [html] view plain copy
     代码二:将Comment中的字符COPY至Name中
     Option Explicit
     ValidationMode = True
     InteractiveMode = im_Batch

     Dim mdl ' the current model

     ' get the current active model
     Set mdl = ActiveModel
     If (mdl Is Nothing) Then
     MsgBox "There is no current Model "
     ElseIf Not mdl.IsKindOf(PdPDM.cls_Model) Then
     MsgBox "The current model is not an Physical Data model. "
     Else
     ProcessFolder mdl
     End If

     Private sub ProcessFolder(folder)
     On Error Resume Next
     Dim Tab 'running   table
     for each Tab in folder.tables
     if not tab.isShortcut then
     tab.name = tab.comment
     Dim col ' running column
     for each col in tab.columns
     if col.comment="" then
     else
     col.name= col.comment
     end if
     next
     end if
     next

     Dim view 'running view
     for each view in folder.Views
     if not view.isShortcut then
     view.name = view.comment
     end if
     next

     ' go into the sub-packages
     Dim f ' running folder
     For Each f In folder.Packages
     if not f.IsShortcut then
     ProcessFolder f
     end if
     Next
     end sub

     */



    //THIRD METHOD
    /**
     [html] view plain copy
     代码三：将Name中的字符COPY至Comment中（优化）
     '把pd中那么name想自动添加到comment里面
     '如果comment为空,则填入name;如果不为空,则保留不变,这样可以避免已有的注释丢失.

     Option Explicit
     ValidationMode = True
     InteractiveMode = im_Batch

     Dim mdl ' the current model

     ' get the current active model
     Set mdl = ActiveModel
     If (mdl Is Nothing) Then
     MsgBox "There is no current Model "
     ElseIf Not mdl.IsKindOf(PdPDM.cls_Model) Then
     MsgBox "The current model is not an Physical Data model. "
     Else
     ProcessFolder mdl
     End If

     ' This routine copy name into comment for each table, each column and each view
     ' of the current folder
     Private sub ProcessFolder(folder)
     Dim Tab 'running   table
     for each Tab in folder.tables
     if not tab.isShortcut then
     if trim(tab.comment)="" then '如果有表的注释,则不改变它.如果没有表注释.则把name添加到注释里面.
     tab.comment = tab.name
     end if
     Dim col ' running column
     for each col in tab.columns
     if trim(col.comment)="" then '如果col的comment为空,则填入name,如果已有注释,则不添加;这样可以避免已有注释丢失.
     col.comment= col.name
     end if
     next
     end if
     next

     Dim view 'running view
     for each view in folder.Views
     if not view.isShortcut and trim(view.comment)="" then
     view.comment = view.name
     end if
     next

     ' go into the sub-packages
     Dim f ' running folder
     For Each f In folder.Packages
     if not f.IsShortcut then
     ProcessFolder f
     end if
     Next
     end sub
     *
     */
}
