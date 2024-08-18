package com.example.quizapplication

object Constants {

    const val USER_NAME : String = "user_name"
    const val TOTAL_QUESTIONS : String = "total_questions"
    const val CORRECT_ANSWERS : String = "correct_answers"

    fun getQuestions() : ArrayList<Question>{
        val questionsList = ArrayList<Question>()

        val q1 = Question(
            1, "What property of aluminium makes it ideal for use in heat-dissipating applications?",
            R.raw.sq1,
            "Good Thermal Conductivity","High electrical resistance","high melting point","low density",
            1
        )
        questionsList.add(q1)

        val q2 = Question(
            2, "What characteristic of boron is linked to its ability to form covalent bonds?",
            R.raw.sq2,
            "excellent conductor of electricity", "poor conductor of electricity","forms ionic bonds","has a metallic luster",
            2
        )
        questionsList.add(q2)

        val q3 = Question(
             3,"Which material is primarily used in pencils due to its carbon content?",
            R.raw.sq3,
            "Graphite", "Clay","Plastic","Wood",
            1
        )
        questionsList.add(q3)

        val q4 = Question(
            4, "What is the primary purpose of adding fluoride to public water supplies?",
            R.raw.sq4,
            "To improve water taste", "To increase water hardness","To reduce the incidence of cavities","to remove contaminants from water",
            3
        )
        questionsList.add(q4)

        val q5 = Question(
            5, "Which property of lead allows it to be easily shaped into sheets or other forms?",
            R.raw.sq5,
            "Conductivity", "Malleability","Brittleness","Rigidity",
            2
        )
        questionsList.add(q5)


        val q6 = Question(
            6, "What are the sensory characteristics of oxygen under standard conditions?",
            R.raw.sq6,
            " Colorless, with a distinct odor and taste", "Greenish, with a strong smell"," Blue, odorless, and tasteless","Colorless, odorless, and tasteless",
            4
        )
        questionsList.add(q6)

        val q7 = Question(
            7, "Which of the following characteristics is true for sodium?",
            R.raw.sq7,
            "It remains stable in water", "It reacts explosively with water"," It forms a protective oxide layer ","It is used in jewelry due to its luster",
            2
        )
        questionsList.add(q7)

        val q8 = Question(
            8, "What is the typical color of elemental sulfur in its natural form?",
            R.raw.sq8,
            "Red", "Black","Dark Green","Bright Yellow",
            4
        )
        questionsList.add(q8)

        val q9 = Question(
            9, "What is notable about xenon compared to other noble gases?",
            R.raw.sq9,
            "Lightest noble gas", "reactive noble gas","heaviest noble gas","least dense noble gas",
            3
        )
        questionsList.add(q9)

        val q10 = Question(
            10, "What does it mean for neon to be chemically inert?",
            R.raw.sq10,
            " It does not react with other elements or compounds", " It reacts readily with other elements"," It forms compounds with most other gases"," It is highly reactive and combustible",
            1
        )
        questionsList.add(q10)

        return questionsList
    }
}