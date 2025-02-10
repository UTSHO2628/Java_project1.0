function fetchStudents() {
    fetch('/students')
        .then(response => response.json())
        .then(data => {
            let table = document.getElementById("studentTable");
            table.innerHTML = "";
            data.forEach(student => {
                let row = `<tr>
                    <td>${student.id}</td>
                    <td>${student.name}</td>
                    <td>${student.age}</td>
                    <td>${student.email}</td>
                    <td>${student.phone}</td>
                </tr>`;
                table.innerHTML += row;
            });
        })
        .catch(error => console.error('Error fetching students:', error));
}
