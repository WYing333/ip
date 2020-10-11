# User Guide

## Table of content

1. [Introduction](#1-introduction)
2. [Quick Start](#2-quick-start)
3. [Features and Usage](#3-features-and-usage)
    
    3.1 [Add ToDo: `todo`](#31-add-todo-todo)
    
    3.2 [Add Event: `event`](#32-add-event-event)

    3.3 [Add Deadline: `deadline`](#33-add-deadline-deadline)

    3.4 [List All Tasks: `list`](#34-list-all-list)
    
    3.5 [Mark as Done: `done`](#35-mark-as-done-done)

    3.6 [Delete task: `delete`](#36-delete-task-delete)

    3.7 [Find by key word: `find`](#37-find-by-key-word-find)
    
    3.8 [Exit the program: `bye`](#39-exit-the-program-bye)
    
4. [Additional Notes](#5-additional notes)


## 1. Introduction
Duke is a task management application, which is designed for users who prefer 
to work with a Command Line Interface (CLI) to manage their tasks efficiently. 


## 2. Quick Start 
1. Download and install Java 11 or above on your machine.
2. Download the latest ip.jar file.
3. Copy the .jar file into a folder that you wish to use as the home directory
   of this application
4. Open command line app and go the home directory of the application and run
    ```
    java -jar ip.jar
    ```

## 3. Features and Usage

#### Command Format
Command word is case-sensitive e.g. `todo` cannot be entered as `TODO`.

### 3.1 Add Todo: `todo`
Add a `todo` task to the task list	

Format: ```todo <Task Description>```

Example 
```todo return books```

Expected outcome:
```
Got it. I've added this task:
   [T][✘] return books
```

### 3.2 Add Event: `event`
Add a `event` task to the task list

Format: ```event <Task Description> /at <Date>```

This supports date input of multiple format. 

If the input is not in the listed formats, the 
exact user input will be not be recognised as a valid date 
and the code will print "null" instead of the exact user input.

Example 1
```event CS2113 tutorial /at 2020 02 02```

Expected outcome:
```
Got it. I've added this task:
[E][✘] CS2113 tutorial (at: 2020-02-02T00:00)
```

Example 2
```event CS2113 quiz /at Monday```

Expected outcome:
```
Got it. I've added this task:
[E][✘] CS2113 quiz (at: null)
```

### 3.3 Add Deadline: `deadline`
Add a `deadline` task to the task list

Format: ```deadline <Task Description> /by <Date>``` 

This supports date input of multiple format.

If the input is not in the listed formats, the 
exact user input will be not be recognised as a valid date 
and the code will print "null" instead of the exact user input.

Example 
```deadline IP submission /by 20190302```

Expected outcome:
```
Got it. I've added this task:
[D][✘] IP submission (by: 2019-03-02T00:00)
```

Example 
```deadline go out to eat /by today```

Expected outcome:
```
Got it. I've added this task:
[D][✘] go out to eat (by: null)
```

### 3.4 List All Tasks: `list`
Display all the task.

Format: ```list```

Example 
```list```

Expected outcome:
```
1. [T][✘] return books
2. [E][✘] CS2113 tutorial (at: 2020-02-02T00:00)
```

### 3.5 Mark as Done: `done`
Mark a task as done.

Format: ```done <index>```

The index corresponds to the index of the task when they are
displayed in the list command. If the index falls out of the
range, no action will be performed.

Example 
```done 1```

Expected outcome:
```
Nice! I've marked this task as done:
[T][✓] return books
```

### 3.6 Delete task: `delete`
Delete a task.

Format: ```delete <index>```

The index corresponds to the index of the task when they are
displayed in the list command. If the index falls out of the
range, no action will be performed.

Example 
```delete 1```

Expected outcome:
```
Got it. I've removed this task:
[T][✓] return books
```

### 3.7 Find by key word: `find`
Find all tasks with description that contains the query key word.

Format: ```find <key word>```

Example 
```find tutorial```

Expected outcome:
```
1. [E][✘] CS2113 tutorial (at: 2020-02-02T00:00)
```

### 3.8 Exit the program: `bye`
Exit the program.

Example 
```bye```

Expected outcome:
```
Bye. Hope to see you again soon!
```

## 4. Additional Notes
1) Here are all 3 valid formats.
    
    `yyyyMMdd`
    `yyyy-MM-dd`
    `yyyy MM dd`

2) For windows machine, need to configure the terminal 
to get the "✓" instead of the "?".

      type `chcp 65001`in your cmd

      then try run jar by: `java -Dfile.encoding=UTF-8 -jar ip.jar`
   
3) Due to the implementation of the DateParse, if the time provided by user is invalid time, 
then the null will be returned instead of the invalid string.
