fun main(args: Array<String>) {
    var playing = true
    var choice: String
    val utils = Utils()
    val family = FamilyMembers(utils)
    val santa = SantaPicker(utils)


    println("\nHello! Welcome to the Secret Santa selector.")
    while (playing) {
        println("\nEnter the number corresponding to what you want to do!\n" +
                "1. Randomly pick who your Secret Santa target will be.\n" +
                "2. View list of family members.\n" +
                "3. Add family members to list.\n" +
                "4. Remove family members from list.\n" +
                "5. Exit")
        choice = readln()

        when (choice) {
            "1" -> santa.pick()
            "2" -> family.show()
            "3" -> family.addNew()
            "4" -> family.remove()
            "5" -> playing = false
            else -> println("\nInvalid answer.\n")
        }
    }
    println("\nGoodbye!")
}