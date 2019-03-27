// Order.ts 
// A Typescript / javascript file to hold food items for ordering

interface Food
{
	name: string;
	price: number;
	priceLarge?: number;
	info?: string[];
}

let buffBites: Food = {name: "Buffalo Califlower Bites",price: 5.95};
let kaleSald: Food = {name: "Kale Salad",price: 6.95, info: ["GL","SF"]};
let tots: Food = {name: "Tater Tots",price: 3.95,info: ["GL"]};
let macNCheese: Food = {name: "Mac 'N Cheese",price: 6.49,info: ["GL"]};
let chilli: Food = {name: "Lulu's Chilli",price: 5.89,info: ["GL","NF"]};
let nachos: Food = {name: "Loaded Nachos",price: 8.95,info: ["GL"]};
let vTots: Food = {name: "Volcano Tots",price: 5.95,priceLarge: 8.95,info: ["GL"]};
let nTots: Food = {name: "Loaded Nacho Tots",price: 6.95,priceLarge: 9.95,info: ["GL"]};
let kbsCaeser: Food = {name: "Kale and Brussles Sprouts Caesar Salad",price: 8.95,info: ["GL"]};

//let starters:Food[] = [buffBites,kaleSald,tots,macNCheese,chilli,nachos,vTots,ntots,kbsCaeser];

// Wraps &amp; Sandwiches;
let falfel: Food = {name: "Sweet Potato Falafel",price: 6.95};
let buffCaeserW: Food = {name: "Buffalo Caesar Wrap",price: 8.95};
let buffCauliW: Food = {name: "Buffalo Cauliflower Wrap",price: 7.95};
let spbbBurger: Food = {name: "Sweet Potato Black Bean Burger",price: 6.95};
let buffBlueBurger: Food = {name: "Buffalo Blue Burger",price: 6.95};
let robo: Food = {name: "The Robo Special",price: 7.95};
let sliders: Food = {name: "Sliders",price: 8.95};
let lnCrunch: Food = {name: "Loaded Nacho Crunch Wrapӧ",price: 8.95};
let winter: Food = {name: "Winter Quinoa Wrap",price: 8.95};
let tacos: Food = {name: "Carnitas Tacos",price: 7.95,info: ["GL"]};

//Entrees
let bEnchi: Food = {name: "Butternut Squash Enchiladas",price: 8.95};
let wFlatbread: Food = {name: "Winter Flatbread",price: 7.95};
let wqBowl: Food = {name: "Winter Quinoa Bowl",price: 9.95,info: ["GL"]};
let mStro: Food = {name: "Mushroom Stroganoff",price: 9.95,info: ["GL"]};

// KIDS MENU
let nuggets: Food = {name: "Cauliflower Nuggets",price: 4.95};
let kNachos: Food = {name: "Kids Nachos", price: 4.95,info: ["GL"]};
let miniB: Food = {name: "Mini Burger(1)",price: 3.95};
let kTots: Food = {name: "Cheesy Tots",price: 4.95,info: ["GL"]};
let quesa: Food = {name: "Kids Quesadilla",price: 4.95};
