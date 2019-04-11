"use strict";

/**
 * check strToTest with Luhn algo
 *
 * @param {string} strToTest
 * @param {number} multiple
 * @return {number} the correct number of checksum
 */
function checkLuhn(strToTest: string, multiple: number = 10): number
{
	let digit: number = 0;
	let sum: number = 0;
	let length: number = strToTest.length;
	let odd: boolean = false;

	for (let i: number = (length - 1); i >= 0; i--)
	{
		digit = parseInt(strToTest[i],10) | 0;

		if (odd == true)
		{
			digit = digit * 2 | 0;
		}
		if (digit > 9)
		{
			digit = digit - 9;
		}
		odd = !odd;
		sum += digit;
	}
	let res: number = sum % multiple;
	if (res === 0)
	{
		return 0;
	}
	return multiple - (res);
}

/**
 * corrected strToTest
 * @param {string} strToTest
 * @param {number} multiple
 * @return {string} valid Luhn
 */
function correctedLuhn(strToTest: string,multiple: number = 10): string
{
	let ret: number = checkLuhn(strToTest,multiple);

	if(ret !== 0)
	{
		let digit = parseInt(strToTest[(strToTest.length - 1)]);
		if ((digit + ret) > 9)
		{
			throw new Error("Too much number to be corrected");
		}
		let copy = strToTest.substr(0,strToTest.length - 1);
		return copy.concat((digit + ret).toString());
	}

	return strToTest;
}

/**
 * @param {string} strToTest
 * @param {number} multiple
 * @return {number[]}
 */
function computeCheckSumControl(strToTest: string,multiple: number = 10): number[]
{
	let ret: number[] = [];
	let i: number = 0;
	let checkSumTest: number = 0;
	let copy: string;

	while(i < 10)
	{
		copy = String(strToTest).concat(i.toString());
		checkSumTest = checkLuhn(copy,multiple) | 0;
		i += checkSumTest;
		if (i < 10)
		{
			ret.push(i)
			i++
		}
	}
	return ret;
}

/**
 * Return false if strToTest is empty or checkLuhn != 0
 * @param {string} strToTest
 * @param {number} multiple
 * @return {boolean}
 */
function isLuhnValid(strToTest: string, multiple: number = 10): boolean
{
	if (strToTest.length === 0)
	{
		return false;
	}
	let ret: boolean = (checkLuhn(strToTest,multiple) === 0);
	return ret;
}

/**
 * return a clean copy of strToTest
 * @param {string} strToTest
 * @return {string}
 */
function cleanProposed(strToTest: string): string
{
	let cleanStr: string = String(strToTest).replace(/[^\d]/g,"");
	return cleanStr;
}

export {isLuhnValid};
export {checkLuhn};
export {computeCheckSumControl};
export {correctedLuhn};
export {cleanProposed};
