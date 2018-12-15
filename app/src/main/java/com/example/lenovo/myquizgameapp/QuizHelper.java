package com.example.lenovo.myquizgameapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class QuizHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DB_NAME = "TQuiz.db";

    private static final int DB_VERSION = 3;
    //Table name
    private static final String TABLE_NAME = "TQ";
    //Id of question
    private static final String UID = "_UID";
    //Question
    private static final String QUESTION = "QUESTION";
    //Option A
    private static final String OPTA = "OPTA";
    //Option B
    private static final String OPTB = "OPTB";
    //Option C
    private static final String OPTC = "OPTC";
    //Option D
    private static final String OPTD = "OPTD";
    //Answer
    private static final String ANSWER = "ANSWER";

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( " + UID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + QUESTION + " VARCHAR(255), " + OPTA + " VARCHAR(255), " + OPTB + " VARCHAR(255), " + OPTC + " VARCHAR(255), " + OPTD + " VARCHAR(255), " + ANSWER + " VARCHAR(255));";
    //Drop table query
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;


    public QuizHelper( Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP_TABLE);
        onCreate(sqLiteDatabase);
    }

    void allQuestion() {
        ArrayList<Questions> arraylist = new ArrayList<>();

        arraylist.add(new Questions("Galileo was an Italian astronomer who developed?", "Telescope", "Airoplane", "Electricity", "Train", "Telescope"));

        arraylist.add(new Questions("Who is the father of Geometry ?", "Aristotle", "Euclid", "Pythagoras", "Kepler", "Euclid"));

        arraylist.add(new Questions("The first woman in space was ?", "Valentina Tereshkova", "Sally Ride", "Naidia Comenci", "Tamara Press", "Valentina Tereshkova"));

        arraylist.add(new Questions("Who is the Flying Sikh of India ?", "Mohinder Singh", "Joginder Singh", "Ajit Pal Singh", "Milkha singh", "Milkha singh"));

        arraylist.add(new Questions("The first nuclear power plant in Pakistan was established at:", "Chashma", "Karachi", "Mianwali", "Multan", "Karachi"));

        arraylist.add(new Questions("Who is Larry Pressler ?", "Politician", "Painter", "Actor", "Tennis player", "Politician"));

        arraylist.add(new Questions("Michael Jackson is a distinguished person in the field of ?", "Pop Music", "Jounalism", "Sports", "Acting", "Pop Music"));

        arraylist.add(new Questions("Who wrote national anthem of Pakistan ?", " Allama Iqbal", "Ismail Merathi", "Mihir Sen", "Hafeez Jalandri", "Hafeez Jalandri"));

        arraylist.add(new Questions(" Who invented the telephone ?", "Graham Bell", " Markoni", " Albert Einstien", "V. Shantaram", "Graham Bell"));

        arraylist.add(new Questions("Who is known as the ' Saint of the gutters ?", "B.R.Ambedkar", "Mother Teresa", "Mahatma Gandhi", "Baba Amte", "Mother Teresa"));

        arraylist.add(new Questions("Who invented the famous formula E=mc^2", "Albert Einstein", "Galilio", "Sarvesh", "Bill Gates", "Albert Einstein"));

        arraylist.add(new Questions("Who is elected as president of us 2016", "Donald Trump", "Hilary Clinton", "Jhon pol", "Barack Obama", "Donald Trump"));

        arraylist.add(new Questions("Who was the founder of company Microsoft", "Bill Gates", "Bill Clinton", "Jhon rio", "Steve jobs", "Bill Gates"));

        arraylist.add(new Questions("Who was the founder of company Apple ?", "Steve Jobs", "Steve Washinton", "Bill Gates", "Jobs Wills", "Steve Jobs"));

        arraylist.add(new Questions("Who was the founder of company Google ?", "Steve Jobs", "Bill Gates", "Larry Page", "Sundar Pichai", "Larry Page"));

        arraylist.add(new Questions("Who is know as god of cricket ?", "Sachin Tendulkar", "Kapil Dev", "Virat Koli", "Dhoni", "Sachin Tendulkar"));

        arraylist.add(new Questions("who has won ballon d'or of 2015 ?", "Lionel Messi", "Cristiano Ronaldo", "Neymar", "Kaka", "Lionel Messi"));

        arraylist.add(new Questions("The first Rocket launched by Pakistan was:", "Rahbar", "Ghori", "Badar", "Kaka", "Rahbar"));

        arraylist.add(new Questions("Where is the smallest bone in the body ?", "Ear", "Nose", "Eye", "Finger", "Ear"));

        this.addAllQuestions(arraylist);

    }
    private void addAllQuestions(ArrayList<Questions> allQuestions) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            for (Questions question : allQuestions) {
                values.put(QUESTION, question.getQuestion());
                values.put(OPTA, question.getOpta());
                values.put(OPTB, question.getOptb());
                values.put(OPTC, question.getOptc());
                values.put(OPTD, question.getOptd());
                values.put(ANSWER, question.getAnswer());
                db.insert(TABLE_NAME, null, values);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }
    }

    List<Questions> getAllOfTheQuestions() {

        List<Questions> questionsList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        String coloumn[] = {UID, QUESTION, OPTA, OPTB, OPTC, OPTD, ANSWER};
        Cursor cursor = db.query(TABLE_NAME, coloumn, null, null, null, null, null);


        while (cursor.moveToNext()) {
            Questions question = new Questions();
            question.setId(cursor.getInt(0));
            question.setQuestion(cursor.getString(1));
            question.setOpta(cursor.getString(2));
            question.setOptb(cursor.getString(3));
            question.setOptc(cursor.getString(4));
            question.setOptd(cursor.getString(5));
            question.setAnswer(cursor.getString(6));
            questionsList.add(question);
        }

        db.setTransactionSuccessful();
        db.endTransaction();
        cursor.close();
        db.close();
        return questionsList;
    }
}
