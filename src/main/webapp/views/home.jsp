<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>NKM India</title>
    <script>

        function insert() {
            var textBox = document.getElementById("myTextBox");
            var text = textBox.value;

            const url = `http://localhost:8080/api/insert`;

            const data = {
                'text': text,
            };

            fetch(url, {
                method: "POST",
                body: JSON.stringify(data),
                headers: {
                    "Content-Type": "application/json",
                },
            })
                .then(response => response.json())
                .then(data => {
                    if (data.success) {
                        console.log("inserted");
                    } else {
                        console.log("not inserted");
                    }
                });


        }

        function displayText() {
            var textBox = document.getElementById("myTextBox");
            var text = textBox.value;

            var listItem = document.createElement("li");
            listItem.appendChild(document.createTextNode(text));
            //
            var deleteButton = document.createElement("button");
            deleteButton.appendChild(document.createTextNode("Delete"));
            deleteButton.onclick = function () {
                deleteEntry(this);
            };

            listItem.appendChild(deleteButton);

            var list = document.getElementById("myList");
            list.appendChild(listItem);
        }

        function deleteEntry(button) {
            var listItem = button.parentNode;
            var text = listItem.firstChild.textContent;

            //removing from db by api

            const url = `http://localhost:8080/api/delete/`.concat(text);
            console.log(url);
            fetch(url, {
                method: "DELETE",
            })
                .then(response => response.json())
                .then(data => {
                    if (data.success) {
                        console.log("deleted");
                    } else {
                        console.log("not deleted");
                    }
                });
            listItem.remove();
        }
    </script>

</head>

<body>
    <input type="text" id="myTextBox">
    <button onclick="displayText(), insert()">Submit</button>
    <ul id="myList"></ul>
</body>

</html>