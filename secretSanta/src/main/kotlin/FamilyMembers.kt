class FamilyMembers constructor(private val utils: Utils) {
    fun show(numbers: Boolean = false) {
        // Displays all the people in either a nice format with their relation to the user
        // Or with numbers based on parameters.
        val family = utils.readFamily()

        println()

        for ((i, member) in family.withIndex()) {
            var member = utils.toMap(member)

            if (numbers) { // More straightforward version with numbers if called with a true.
                println("${i + 1}. ${member["name"]}")
            } else {
                print("You are ${member["name"]}'s ${member["relationship"]}")
                if (member["previousYear"].toBoolean()) { print(" and you picked them last time") }
                println(".")
            }
        }
    }

    fun addNew() {
        // Asks user the info to add stuff to the file.
        print("\nWhat is their name? ")
        val name: String = readln()

        var option: String
        var relationship: String = ""
        var going: Boolean = true

        while(going) {
            going = false
            println("\n" +
                    "Enter the number corresponding to your relationship with them!\n" +
                    "1. Close Family (parent, child, sibling, etc.)\n" +
                    "2. Extended Family (grandparent, grandchild, aunt/uncle, cousin, etc.\n" +
                    "3. Friend/other")
            option = readln()

            when (option) {
                "1" -> relationship = "close family"
                "2" -> relationship = "extended family"
                "3" -> relationship = "friend/other"
                else -> going = true
            }
        }

        utils.writeFamily("name=${name},relationship=${relationship},previousYear=false\n")
    }

    fun remove() {
        // Allows the user to pick a person to remove from the file.
        show(true)
        var pick: String
        val family = utils.readFamily()

        while (true) {
            print("\nWhat number do you wish to remove? ")
            pick = readln()
            try {
                if (pick.toInt() in 1..family.size) { break }
            } catch(e: NumberFormatException) { }
            println("Has to be just a number between 1 and ${family.size}.")
        }
        utils.delete(pick.toInt() - 1)
    }
}