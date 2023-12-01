const synopsisBtn = document.getElementById('synopsisBtn')
const episodeBtn = document.getElementById('episodeBtn')
const reviewBtn = document.getElementById('reviewBtn')

const synopsis = document.getElementById('synopsis')
const episode = document.getElementById('episode')
const review = document.getElementById('review')

function active(content) {

	synopsisBtn.style.backgroundColor = `${(content == 'synopsis')? '#161616': 'transparent'}`
    episodeBtn.style.backgroundColor = `${(content == 'episode')? '#161616':''}`
    reviewBtn.style.backgroundColor = `${(content == 'review')? '#161616':''}`


    synopsis.style.transform = `${(content == 'synopsis')? 'translateX(0)':'translateX(100%)'}`
    episode.style.transform = `${(content == 'episode')? 'translateX(0)':'translateX(100%)'}`
    review.style.transform = `${(content == 'review')? 'translateX(0)':'translateX(100%)'}`
}

const egBtn = document.querySelector('.egBtn')
const mmBtn = document.querySelector('.mmBtn')

const egBox = document.querySelector('.egBox')
const mmBox = document.querySelector('.mmBox')

function change(text){
	egBox.style.transform = `${(text == 'eng')? 'translateX(0)':'translateX(100%)'}`
	mmBox.style.transform = `${(text == 'mm')? 'translateX(0)':'translateX(100%)'}`
}

// comment box-editor change
//const comEditer = document.getElementById('comEditer')
//const comPara = document.getElementById('comPara')

function changeEdit(isEdit,commentId){
	
	const comEditer = document.getElementById('comEditer'+commentId)
	const comPara = document.getElementById('comPara'+commentId)
	
	console.log(isEdit,commentId,comEditer)
	
	comEditer.style.transform = `${(isEdit == 'edit')? 'translateX(0)':'translateX(150%)'}`
	comPara.style.transform = `${(isEdit == 'undo')? 'translateX(0)':'translateX(150%)'}`
}

