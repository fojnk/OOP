import java.time.LocalDate

settings {
    branch = "main"
}

allTasks = [
        {
            name = "Task_1_1_1"
            description = "HeapSort"
            cost = 1
            softDeadline = LocalDate.of(2023, 9, 21)
            hardDeadline = LocalDate.of(2023, 9, 28)
        }
]

studentsList = [
        {
            name = "Vova Petrov"
            username = "fojnk"
            repository = "https://github.com/fojnk/OOP.git"
            tasks = allTasks
        }
]
groups = [
        {
            number = "22216"
            students = studentsList
        }
]