package com.example.practic_8;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.switchmaterial.SwitchMaterial;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Метод для создания всплывающих уведомлений
    public void alertDialogs(String title, String message){
        // Объявляем всплывающее уведомление
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Инициализируем параметры всплывающего уведомления
        builder.setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alter = builder.create();
        // Всплываем уведомление
        alter.show();
    }

    // Метод для конвертации валют
    public void consider(View view){
        // Привязываем переменные к объектам activity_main
        EditText course = findViewById(R.id.course);
        EditText count = findViewById(R.id.count);
        SwitchMaterial dollar = findViewById(R.id.switchButton);
        TextView tv = findViewById(R.id.result);

        // Валидируем ввод на наличие
        if(course.getText().length() > 0){
            if(count.getText().length() > 0){
                // Вносим введенные данные в переменные
                float f_course = Float.parseFloat(String.valueOf(course.getText()));
                float f_count = Float.parseFloat(String.valueOf(count.getText()));

                // Результат
                float composititon = 0;
                // Переводим доллары в рубли при соответствующем положении флажка
                if(dollar.isChecked()){
                    composititon = f_count * f_course;
                    tv.setText(String.format("%s р.", composititon));
                }
                // Рубли в доллары
                else{
                    composititon = f_count / f_course;
                    tv.setText(String.format("%s $", composititon));
                }
            }
            else
                alertDialogs("Уведомление", "Введите кол-во долларов");
        }
        else
            alertDialogs("Уведомление", "Введите курс доллара");
    }

    // Метод для открытия страницы с курсом доллара
    public void URL(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://ru.investing.com/currencies/usd-RUB"));
        startActivity(intent);
    }
}