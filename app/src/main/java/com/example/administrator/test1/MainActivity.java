package com.example.administrator.test1;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ExpandedMenuView;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    public EditText todo,date;
    public Button add,del,show;
    public SharedPreferences sp;
    public SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        todo=(EditText)findViewById(R.id.todo);
        date=(EditText)findViewById(R.id.date);
        add=(Button)findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
            }
        });
        del=(Button)findViewById(R.id.del);
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                del();
            }
        });
        show=(Button)findViewById(R.id.show);
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show();
            }
        });
        sp=getSharedPreferences("data",MODE_PRIVATE); //撈資料
        editor=sp.edit();//寫資料

    }
    private void add(){
        String todoString = todo.getText().toString();
        String dateString = date.getText().toString();

        if (todoString.equals("")){
            Toast.makeText(this, "empty", Toast.LENGTH_SHORT).show();
            return;

        }



        editor.putString(todoString,dateString);
        editor.commit();
        Toast.makeText(this, "Add OK", Toast.LENGTH_SHORT).show();
    }
    private void del(){
        String todoString = todo.getText().toString();
        editor.remove(todoString);
        editor.commit();
        Toast.makeText(this, "Delete OK", Toast.LENGTH_SHORT).show();
    }

    private void show(){

        Map<String,String> data =  (Map<String,String>)sp.getAll();
        // from key
        Set<String> todos = data.keySet();
        String[] allTodo = new String[todos.size()];

        int i = 0;
        for (String todo: todos){
            allTodo[i] = todo + "\n" + sp.getString(todo, "");
            i++;
        }

        AlertDialog dialog = null;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setItems(allTodo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        dialog = builder.create();
        dialog.show();

    }
}
