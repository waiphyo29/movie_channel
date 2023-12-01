// img slider
const initSlider = () => {
    const imageList = document.querySelector(".slider-wrapper .image-list");
    const slideButtons = document.querySelectorAll(".slider-wrapper .slide-button");
    const itemList = document.querySelector(".slider-wrapper .image-list .image-item");
    slideButtons.forEach(button => {
        button.addEventListener("click", () => {
            const direction = button.id === "prev-slide" ? -1 : 1;
            const scrollAmount = (itemList.clientWidth + 16) * direction;
            imageList.scrollBy({left: scrollAmount,behavior: "smooth"});
        });
    });
}

window.addEventListener("load", initSlider);

// sign in box
const signupBox = document.getElementById('signupBox')
const signinBox = document.getElementById('signinBox')

function transfer(box){
	signupBox.style.transform = `${(box == 'signup')? 'translateY(0)':'translateY(-150%)'}`
	signinBox.style.transform = `${(box == 'signin')? 'translateY(0)':'translateY(150%)'}`
}