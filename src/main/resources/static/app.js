let genreform = document.querySelector(".gnr");

genreform.addEventListener("submit", (e) => {

    let filterlist = []
    genreform.genre.forEach(element => {
            if (element.checked) {
                filterlist.push(element.value)
            }
        }
    )
    console.log(filterlist)
})