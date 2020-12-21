package com.example.esameandroid_letiziagiammorcaro

import androidx.lifecycle.ViewModel

class StudentListViewModel: ViewModel() {

    val studentsLists = arrayListOf<Student>(
            Student("Alessandro Claudio","A01",false),
            Student("Alessio Barbagallo","C02",false),
            Student("Tewolde Berhe","AB4",false),
            Student("Roberta Corallo","F09",true),
            Student("Gaetano Crisafulli","G05", true),
            Student("Sergio Di Bella","A70", false),
            Student("Marco di Stefano","C43", true),
            Student("Letizia Giammorcaro","F03", true),
            Student("Christian La Tona","B01", false),
            Student("Andrea Lo Monaco","H32",true),
            Student("Vincenzo Marziano","L09",true ),
            Student("Massimiliano Maugeri","F05", false)
    )


    init{
        for (i in 0 until studentsLists.size){
            val student = Student(studentsLists[i].name,studentsLists[i].macBook)
            studentsLists += student
        }
    }
}

