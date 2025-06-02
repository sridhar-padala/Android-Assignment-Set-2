# Android-Assignment-Set-2
NQueens, ModuleLoader, and WeatherTrack Projects
Overview
This repository contains three distinct projects:  

NQueens Solver: A C++ solution for the N-Queens problem, finding all valid placements of queens on an NxN chessboard.  
ModuleLoader: A C++ program to detect circular dependencies in a directed graph of modules.  
WeatherTrack: An Android app to display and manage mock weather data for Vijayawada with a weekly summary.

Project Details
1. NQueens Solver
Description
Solves the classic N-Queens problem, where N queens must be placed on an NxN chessboard such that no two queens attack each other (no shared row, column, or diagonal).
Features

Accepts user input for board size N (1 to 9).
Uses backtracking to find all valid queen placements.
Displays each solution as a board with 'Q' for queens and '.' for empty cells.

Key Components

NQueensSolver Class:
solveNQueens(int n): Public method to initiate solving and return all solutions.
placeQueens: Recursive function using backtracking to place queens row by row.
isSafe: Checks if a queen can be placed at a position without conflicts.


Main Function:
Takes user input for N.
Prints total number of solutions and each board configuration.



Usage

Compile: g++ nqueens.cpp -o nqueens
Run: ./nqueens
Enter a value for N (e.g., 4) and view all possible solutions.

Example
For N = 4, sample output might include boards like:  
.Q..
...Q
Q...
..Q.

..Q.
Q...
...Q
.Q..

2. ModuleLoader
Description
A C++ program to detect circular dependencies in a directed graph, useful for scenarios like module or software dependency analysis.
Features

Takes a number of nodes (n) and a list of edges (dependencies).
Uses Depth-First Search (DFS) to identify cycles.
Outputs true for cyclic dependencies, false otherwise.

Key Components

ModuleLoader Class:
hasCircularDependency(int n, vector<vector<int>>& edges): Builds a graph and checks for cycles.
dfsHasCycle: Recursive DFS to detect back edges indicating a cycle.


State Tracking:
Uses a state vector: 0 (unvisited), 1 (visiting), 2 (visited).


Main Function:
Tests two sample cases: one without a cycle, one with a cycle.



Usage

Compile: g++ module_loader.cpp -o module_loader
Run: ./module_loader
View results for sample test cases:
{{0, 1}, {1, 2}, {2, 3}} → false (no cycle)
{{0, 1}, {1, 2}, {2, 0}} → true (cycle)



3. WeatherTrack
Description
An Android app that displays daily weather for Vijayawada using mock data, stores it in a Room database, and provides a weekly summary. Background updates run periodically.
Features

Displays current weather: temperature (°C), humidity (%), and condition.
"Refresh Weather" button fetches new mock data.
"View Weekly Summary" shows a 7-day weather history.
Background updates every 6 hours via WorkManager.

Key Components

MainActivity:
Shows current weather via TextViews.
Buttons for refresh and summary navigation.
Observes LiveData for updates.


SummaryActivity:
Displays a scrollable 7-day summary from the database.


WeatherRepository:
Reads mock data from weather_mock.json in assets.
Saves to Room database with timestamps.


WeatherViewModel:
Manages UI data with LiveData.


WeatherWorker:
Periodic background updates using WorkManager.


WeatherDatabase & WeatherDao:
Stores and queries weather data (WeatherEntity).


Layouts:
activity_main.xml: LinearLayout for current weather.
activity_summary.xml: ScrollView for summary.



Prerequisites

Android Studio (latest stable version)
Minimum SDK: API 21 (Android 5.0 Lollipop)
Dependencies: Room, WorkManager, LiveData, ViewModel, org.json

Setup Instructions

Clone the repository.
Add weather_mock.json to app/src/main/assets with sample data (e.g., temperature, humidity, condition, day).
Open in Android Studio and sync with Gradle.
Build and run on a device or emulator.
Test: View weather, refresh data, and check the weekly summary.

Notes

Uses mock JSON data mapped to the current day.
Persists data in Room for offline access.
No real API; assumes Vijayawada as the city.

General Requirements

C++ Projects (NQueens, ModuleLoader):
Compiler: g++ or any C++11-compatible compiler
Standard libraries: <iostream>, <vector>, <string>


WeatherTrack:
Android Studio, Android SDK
Mock data file: weather_mock.json



How to Use

NQueens: Compile and run, input N, view solutions.
ModuleLoader: Compile and run, check sample outputs for cycles.
WeatherTrack: Build in Android Studio, add JSON file, run on device/emulator, test UI and background updates.

Future Improvements

NQueens: Add visualization or support for larger N.
ModuleLoader: Accept custom input for nodes and edges.
WeatherTrack: Integrate real weather API, add city selection, include weather icons.

