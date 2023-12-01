const movieRate = document.getElementById('movieRating').value

const pointFive = document.getElementById('star0.5')
const one = document.getElementById('star1')
const oneHalfe = document.getElementById('star1.5')
const two = document.getElementById('star2')
const twoHalfe = document.getElementById('star2.5')
const three = document.getElementById('star3')
const threeHalfe = document.getElementById('star3.5')
const four = document.getElementById('star4')
const fourHalfe = document.getElementById('star4.5')
const five = document.getElementById('star5')

function rate(){
	
	const rating = parseFloat(movieRate);

    if (rating <= 1) {
        pointFive.checked = true;
    } else if (rating <= 2) {
        one.checked = true;
    } else if (rating <= 3) {
        oneHalfe.checked = true;
    } else if (rating <= 4) {
        two.checked = true;
    } else if (rating <= 5) {
        twoHalfe.checked = true;
    } else if (rating <= 6) {
        three.checked = true;
    } else if (rating <= 7) {
        threeHalfe.checked = true;
    } else if (rating <= 8) {
        four.checked = true;
    } else if (rating <= 9) {
        fourHalfe.checked = true;
    } else if (rating <= 10) {
        five.checked = true;
    }
	
}

rate()

