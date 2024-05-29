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
        },
        {
            name = "Task_1_1_2"
            description = "Polynomial"
            cost = 1
            softDeadline = LocalDate.of(2023, 10, 5)
            hardDeadline = LocalDate.of(2023, 10, 12)
        },
        {
            name = "Task_1_2_1"
            description = "Tree"
            cost = 1
            softDeadline = LocalDate.of(2023, 10, 19)
            hardDeadline = LocalDate.of(2023, 10, 26)
        },
        {
            name = "Task_1_2_2"
            description = "Graph"
            cost = 1
            softDeadline = LocalDate.of(2023, 11, 2)
            hardDeadline = LocalDate.of(2023, 11, 9)
        },
        {
            name = "Task_1_3_1"
            description = "Substring"
            cost = 1
            softDeadline = LocalDate.of(2023, 11, 2)
            hardDeadline = LocalDate.of(2023, 11, 9)
        },
        {
            name = "Task_1_4_1"
            description = "RecordBook"
            cost = 1
            softDeadline = LocalDate.of(2023, 11, 2)
            hardDeadline = LocalDate.of(2023, 11, 9)
        },
        {
            name = "Task_1_5_1"
            description = "Calculator"
            cost = 1
            softDeadline = LocalDate.of(2023, 11, 2)
            hardDeadline = LocalDate.of(2023, 11, 9)
        },
        {
            name = "Task_1_5_2"
            description = "Notebook"
            cost = 1
            softDeadline = LocalDate.of(2023, 11, 2)
            hardDeadline = LocalDate.of(2023, 11, 9)
        }
]

studentsList = [
        {
            name = "Vova Petrov"
            username = "fojnk"
            repository = "https://github.com/fojnk/OOP.git"
            tasks = allTasks
        },
        {
            name = "Kirill Macanin"
            username = "McAninKirill"
            repository = "https://github.com/McAninKirill/OOP"
            tasks = allTasks
        },
        {
            name = "Ruslan Chudinov"
            username = "VictorPetrovichBarinov"
            repository = "https://github.com/ViktorPetrovichBarinov/OOP"
            tasks = allTasks
        },
        {
            name = "Rustam Khamidullin"
            username = "reflex"
            repository = "https://github.com/Rustam-Khamidullin/OOP"
            tasks = allTasks
        },
        {
            name = "Andrew Kozubenko"
            username = "yes"
            repository = "https://github.com/andrew-kozubenko/OOP"
            tasks = allTasks
        }
]
groups = [
        {
            number = "22216"
            students = studentsList
        }
]