package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton [][] buttons= new ImageButton[3][3];
    private int[] gameState={2,2,2,2,2,2,2,2,2};
    private boolean player1Turn=true;
    private int roundCount;
    private int flag=0;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView= (TextView)findViewById(R.id.mainactivity_title);

        for (int i=0; i<3; i++){
            for (int j=0; j<3; j++){
                String buttonId= "mainactivity_"+i+j;
                int resId= getResources().getIdentifier(buttonId,"id",getPackageName());
                buttons[i][j]=(ImageButton)findViewById(resId);
                buttons[i][j].setOnClickListener(this);
            }
        }
        int click=0;
        Button gamePlay=(Button) findViewById(R.id.mainactivity_play);
        gamePlay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                textView.setText("TIC TAC TOW");
                playAgain();
            }
        });
    }


// 9 Matrix buttons
    public void onClick(View v) {
        int position= Integer.parseInt(v.getTag().toString());
        Button replay=(Button)findViewById(R.id.mainactivity_play);
        replay.setText("REMATCH");
        //If the specific cell is not empty do not do anything.
        if(gameState[position] !=2||flag!=0){
            return;
        }
        if(player1Turn){
            v.setBackgroundResource(R.drawable.x_icon);
            gameState[position] = 0;
        }else{
            v.setBackgroundResource(R.drawable.o_icon);
            gameState[position] = 1;
        }

        roundCount++;

        if(checkForWin(0)){
            textView.setText("X Won");
            flag=1;
        }
        else if(checkForWin(1)){
            textView.setText("O Won");
            flag=1;
        }else if(roundCount==9){
        textView.setText("Draw");
            flag=1;
        }
        else{
            player1Turn= !player1Turn;
        }
    }

    private void playAgain() {
        roundCount =0;
        flag=0;
        player1Turn = true;
        for(int i=0;i<3;i++)
        {
            for(int j=0; j<3; j++){
                buttons[i][j].setBackgroundResource(0);
            }
        }
        for (int i=0;i<9;i++){
            gameState[i] = 2;
        }
    }


    private boolean checkForWin(int pos){

        boolean win = false;
         //Rows
        if(gameState[0] == gameState[1] && gameState[1] == gameState[2]&& gameState[0]==pos) { win = true;}
        else if(gameState[3] == gameState[4]&& gameState[4] == gameState[5]&& gameState[3]==pos) { win = true;}
        else if(gameState[6] == gameState[7] && gameState[7] == gameState[8]&& gameState[6]==pos){ win = true;}
        //Columns
        else if(gameState[0]==gameState[3]&&gameState[3]==gameState[6]&& gameState[0]==pos){ win = true;}
        else if(gameState[1]==gameState[4]&&gameState[4]==gameState[7]&& gameState[1]==pos){ win = true;}
        else if(gameState[2]==gameState[5]&&gameState[5]==gameState[8]&& gameState[2]==pos){ win = true;}
        //Main diagonal
        else if(gameState[0]==gameState[4]&&gameState[4]==gameState[8]&& gameState[0]==pos){ win = true;}
        //Secondary diagonal
        else if(gameState[2]==gameState[4]&&gameState[4]==gameState[6]&& gameState[2]==pos){ win = true;}

        return win;
    }

}