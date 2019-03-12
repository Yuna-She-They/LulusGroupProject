// Example of a Type Script File. Compiles to a Javascript File

// There are 3 Basic types in TypeScript
let isDone: boolean = false;
let lines: number = 42;
let name: string = "Ben";

// you can omit type if variables are derived from explicit literals
let isDone = false;
let lines = 42;
let name = "Ben";

// When it's impossible ot know, there is the "any" type
let notSure: any = 4;
notSure = "mabye a string instead";
notSure = false;

// use const keyword for constants
const numLivesForCat = 9; //number data type is inferred
// numLivesForCat = 1; //Error

//for Collections there are typed arrays and generic arrays
let list: number[] = [1, 2, 3];
//or use the generic array Type
let list: Array<number> = [1,2,3];

//Typescript has Enumerations
enum Color {
	Red,
	Green,
	Blue
};
let c: Color = Color.Green;

// void is used in the speical case of a function returning nothing
function bigHorribleAlert(): void {
	alert("I'm a litle annoying box!");
}

//functions are first class citizens, support the lambada "fat arrow" syntax and use type
// inference

// the following are equivlant
let f1 = function(i: number): number {return i * i;}
//return type inferred
let f2 = function(i: number) {return i * i;}
//"Fat arrow syntax"
let f3 = (i: number): number => {return i*i;}
//fat arrow syntax with treturn type inferred
let f4 = (i: number) => {return i*i;}
//fat arrow syntax with return type inferred, braceless means no return keyword is needed
let f5 = (i: number) => i * i;

//typescript also has interfaces,
interface Person {
	name: string;
	//optional properties marked with a "?"
	age?: number;
	//and of course functions
	move(): void;
}

//Object that implments the "Person interface"

let p:Person = {name: "Bobby", move: () => {} }};
//objects that have the optional property
let validPerson: Person = {name: "Bobby",age: 42,move: () => {}};
//is not avalid person because age is not a number
//XXX let invalidPerson: Person = {name: "Bobby",age: true};

//interfaces can also describe a function type
interface SearchFunc {
	(source: string, subString: string): boolean;
}
//onlay the parameters' types are important, names are not important
let mySearch: SearchFunc;
mySearch = function (src: string, sub: string) {
	return src.search(sub) != -1;
}

//Classes Members are public by default
class Point {
	//properties
	x: number;

	//constructor
	//public private keywors in this context will generate the boiler plate code for the property
	constructor(x: number, public y: number = 0) {
		// the = 0 tells that y defaults to 0
		this.x = x;
	}

	//Functions
	dist() {
		return Math.sqrt((this.x * this.x)+(this.y * this.y));
	}

	//static members
	static origin = new Point(0,0);
}

//Classes can be explicitly marked as implmenting an interface,
//any missing properties will cause an error at compile time
class PointPerson implements Person {
	name: string;
	move() {};
}

let p1 = new Point(10,20);
let p2 = new Point(25); // y will be 0

// Inhertance
class Point3D extends Point {
	constructor(x: number, y:number, z: number =0) {
		super(x,y);
	}

	//overwrite
	dist() {
		let d = super.dist();
		return Math.sqrt(d*d + this.z * this.z);
	}
}

//Modules "." can be used as Seprator for sub modules

module Geometry {
	export class Square {
		constructor(public sideLength: number = 0) { }
		
		area() {
			return Math.pow(this.sideLength,2);
		}
	}
}

let s1 = new Geometry.Square(5);

//local alias for referencing a module

import G = Geometry;

let s2 = new G.Square(10);

//Generic Classes
class Tuple<T1,T2> {
	constructor(public item1: T1, public item2: T2) {}
}

//interfaces
interface Pair<T> {
	item1: T;
	item2: T;
}

//and functions
let pairToTuple = function<T>(p: Pair<T>) {
	return new Tuple(p.item1,p.item2);
};

let tuple = pairToTuple({item1: "hello",item2:"world"});

//including refrences to a defination file:
/// <refrence path="jquery.d.ts" />

// Template Strings (strings that use back ticks)
// String Interplation with Template Strings
let name = 'Tyrone';
let greeting = `Hi ${name}, how are you?`;
// multi line strings with template strings
let multiline = `This is an example
of a multiline string`;

