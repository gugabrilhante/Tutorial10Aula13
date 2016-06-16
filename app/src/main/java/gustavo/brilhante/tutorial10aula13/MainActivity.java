package gustavo.brilhante.tutorial10aula13;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button downloadBtn;

    TextView resultTextView;

    EditText urlEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        urlEditText = (EditText) findViewById(R.id.url_edittext);
        resultTextView = (TextView) findViewById(R.id.webpage_textview);

        downloadBtn = (Button) findViewById(R.id.download_button);
        downloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncRequestUrl service = new AsyncRequestUrl(MainActivity.this, resultTextView);
                service.execute(urlEditText.getText().toString());
            }
        });
    }
}
