#include <iostream>
#include <vector>
#include <string>

using namespace std;

class Solution {
public:
    void solve (int col,vector<string>& board,vector<vector<string>>& ans, int n){
        if(col == n){
            ans.push_back(board);
            return;
        }
        for(int row = 0; row<n ; row++){
            if(isSafe(row,col,board,n)){
                board[row][col] = 'Q' ;
                solve(col+1,board,ans,n);
                board[row][col] = '.';
            }
        }
    }
    bool isSafe(int row,int col , vector<string> board,int n){
         int newrow = row;
         int newcol = col;
         while(newrow>= 0 && newcol >= 0){
             if(board[newrow][newcol] == 'Q'){
                 return false;   
             }
             newrow--;
             newcol--;
         }
         newcol = col;
         newrow = row;
         while(newcol>=0){
             if(board[newrow][newcol] == 'Q'){
                 return false;
             }
             newcol--;
         }
         newrow = row;
         newcol = col;
         while(newrow<n && newcol>= 0){
             if(board[newrow][newcol] == 'Q'){
                 return false;
             }
             newrow++;
             newcol--;
        }
        return true;
    }
    vector<vector<string>> solveNQueens(int n) {
        vector<vector<string>> ans;
        string s ="";
        for(int i = 0; i<n; i++){
            s += '.';
        }
        vector<string> board(n,s);
        solve(0,board,ans,n);
        return ans;
    }
};

int main() {
    int n;
    cout << "Enter the value of N: ";
    cin >> n;

    Solution sol;
    vector<vector<string>> solutions = sol.solveNQueens(n);

    cout << "Number of solutions: " << solutions.size() << endl;
    for (int i = 0; i < solutions.size(); i++) {
        cout << "Solution " << i + 1 << ":" << endl;
        for (const string& row : solutions[i]) {
            cout << row << endl;
        }
        cout << endl;
    }

    return 0;
}