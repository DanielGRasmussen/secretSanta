import java.io.File
import java.io.FileWriter

class Utils {
    private val path: String = "src\\main\\people.txt"
    fun readFamily(): List<String> {
        // Returns the file contents.
        return File(path).bufferedReader().readLines()
    }

    fun writeFamily(new: String, append: Boolean = true) {
        // Writes a new line to the file with the option to delete everything already in it if append is set to false.
        var f = FileWriter(path, append)
        f.write(new)
        f.close()
    }

    fun delete(index: Int) {
        // Deletes line based on index value.
        val family = readFamily()
        writeFamily("", false) // Clear the file in preparation to add everything back.
        for ((i, line) in family.withIndex()) {
            if (i != index) {
                writeFamily(line + "\n")
            }
        }
    }

    fun toMap(s: String): Map<String, String> {
        // Turns a string formatted like:
        // abc=def,ghi=jkl
        // Into a map while I try to wrap my head around the way you are supposed to do it.
        return s.split(",")
            .map { it.split("=") }
            .map { it.first() to it.last().toString() }
            .toMap()
    }
}