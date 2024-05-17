package com.example.notes;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;

import com.example.notes.db.DBHelper;

public class DetailActivity extends AppCompatActivity {

    String tempTitle, tempContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        String writeMode = intent.getStringExtra("writeMode");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        assert writeMode != null;
        getSupportActionBar().setTitle(writeMode.equals("create") ? "Tambah Catatan" : "Ubah Catatan");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);

        DBHelper dbHelper = new DBHelper(this);

        EditText title = findViewById(R.id.titleInput);
        EditText content = findViewById(R.id.noteInput);

        if (writeMode.equals("edit")) {
            tempTitle = dbHelper.getNoteById(intent.getIntExtra("id", 0)).getTitle();
            tempContent = dbHelper.getNoteById(intent.getIntExtra("id", 0)).getContent();
        }

        Button button = findViewById(R.id.submitNote);
        button.setText(writeMode.equals("create") ? "Simpan" : "Ubah");

        title.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (writeMode.equals("edit")) {
                    if ((s.toString().equals(tempTitle) && content.getText().toString().equals(tempContent))) {
                        button.setEnabled(false);
                    } else {
                        button.setEnabled(true);
                    }
                } else {
                    if ((s.toString().trim().isEmpty() && content.getText().toString().trim().isEmpty()) || ((!s.toString().trim().isEmpty() && content.getText().toString().trim().isEmpty()) )) {
                        button.setEnabled(false);
                    } else {
                        button.setEnabled(true);
                    }
                }

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if (writeMode.equals("edit")) {
                    if ((s.toString().equals(tempTitle) && content.getText().toString().equals(tempContent))) {
                        button.setEnabled(false);
                    } else {
                        button.setEnabled(true);
                    }
                } else {
                    if ((s.toString().trim().isEmpty() && content.getText().toString().trim().isEmpty()) || ((!s.toString().trim().isEmpty() && content.getText().toString().trim().isEmpty()) )) {
                        button.setEnabled(false);
                    } else {
                        button.setEnabled(true);
                    }
                }
            }
        });

        content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (writeMode.equals("edit")) {
                    if ((s.toString().trim().isEmpty() && !title.getText().toString().trim().isEmpty()) ||
                            (s.toString().equals(tempContent) && title.getText().toString().equals(tempTitle))) {
                        button.setEnabled(false);
                    } else {
                        button.setEnabled(true);
                    }
                } else {
                    if ((s.toString().trim().isEmpty() && title.getText().toString().trim().isEmpty()) || ((s.toString().trim().isEmpty() && !title.getText().toString().trim().isEmpty()) )) {
                        button.setEnabled(false);
                    } else {
                        button.setEnabled(true);
                    }
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if (writeMode.equals("edit")) {
                    if ((s.toString().trim().isEmpty() && !title.getText().toString().trim().isEmpty()) ||
                            (s.toString().equals(tempContent) && title.getText().toString().equals(tempTitle))) {
                        button.setEnabled(false);
                    } else {
                        button.setEnabled(true);
                    }
                } else {
                    if ((s.toString().trim().isEmpty() && title.getText().toString().trim().isEmpty()) || ((s.toString().trim().isEmpty() && !title.getText().toString().trim().isEmpty()) )) {
                        button.setEnabled(false);
                    } else {
                        button.setEnabled(true);
                    }
                }

            }
        });

        if (writeMode.equals("edit")) {
            int id = intent.getIntExtra("id", 0);
            title.setText(dbHelper.getNoteById(id).getTitle());
            content.setText(dbHelper.getNoteById(id).getContent());
        }

        button.setOnClickListener(view -> {
            if (writeMode.equals("create")) {
                if (title.getText().toString().trim().isEmpty() && content.getText().toString().trim().isEmpty()) {
                    new AlertDialog.Builder(this)
                            .setTitle("Peringatan")
                            .setMessage("Judul dan isi catatan tidak boleh kosong")
                            .setPositiveButton(android.R.string.ok, null)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                } else {
                    String titleText = title.getText().toString();
                    String contentText = content.getText().toString();
                    dbHelper.addNote(titleText, contentText);
                    NavUtils.navigateUpFromSameTask(this);
                }
            } else {
                int id = intent.getIntExtra("id", 0);
                String titleText = title.getText().toString();
                String contentText = content.getText().toString();
                dbHelper.updateNote(id, titleText, contentText);
                NavUtils.navigateUpFromSameTask(this);
            }
        });

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                new AlertDialog.Builder(DetailActivity.this)
                        .setTitle("Batalkan")
                        .setMessage("Apakah Anda yakin ingin membatalkan perubahan?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                NavUtils.navigateUpFromSameTask(DetailActivity.this);
                            }
                        })
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu, menu);
        Intent intent = getIntent();
        String writeMode = intent.getStringExtra("writeMode");
        if (writeMode != null && writeMode.equals("create")) {
            MenuItem item = menu.findItem(R.id.delete);
            item.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.delete) {
            new AlertDialog.Builder(this)
                .setTitle("Hapus Catatan")
                .setMessage("Apakah Anda yakin ingin menghapus catatan ini?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = getIntent();
                        int idNote = intent.getIntExtra("id", 0);
                        DBHelper dbHelper = new DBHelper(DetailActivity.this);
                        dbHelper.deleteNote(idNote);
                        NavUtils.navigateUpFromSameTask(DetailActivity.this);
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
            return true;
        }

        if (id == android.R.id.home) {
            new AlertDialog.Builder(this)
                .setTitle("Batalkan")
                .setMessage("Apakah Anda yakin ingin membatalkan perubahan?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        NavUtils.navigateUpFromSameTask(DetailActivity.this);
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}