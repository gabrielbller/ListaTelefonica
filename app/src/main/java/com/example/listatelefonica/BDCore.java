//package com.example.listatelefonica;

//import android.content.Context;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//public class BDCore extends SQLiteOpenHelper {
//
//    private static final char NOME_BD = 'T';
//    private static final int VERSAO_BD = 1 ;
//
//
//    public BDCore(Context ctx){
//        super(ctx, NOME_BD, null, VERSAO_BD);
//    }
//
//
//    @Override
//    public void onCreate(SQLiteDatabase bd) {
//        bd.execSQL("create table listatelefonica(_id integer primeary key autoincrement, nome text not null, ultimonome text not null, telefone text not null, residencial text not null);");
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase bd, int oldVersion, int newVersion) {
//        bd.execSQL("drop table listatelefonica;");
//        onCreate(bd);
//    }
//}
