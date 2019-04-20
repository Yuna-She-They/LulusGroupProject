"use strict";
exports.__esModule = true;
/**
 * check strToTest with Luhn algo
 *
 * @param {string} strToTest
 * @param {number} multiple
 * @return {number} the correct number of checksum
 */
function checkLuhn(strToTest, multiple) {
    if (multiple === void 0) { multiple = 10; }
    var digit = 0;
    var sum = 0;
    var length = strToTest.length;
    var odd = false;
    for (var i = (length - 1); i >= 0; i--) {
        digit = parseInt(strToTest[i], 10) | 0;
        if (odd == true) {
            digit = digit * 2 | 0;
        }
        if (digit > 9) {
            digit = digit - 9;
        }
        odd = !odd;
        sum += digit;
    }
    var res = sum % multiple;
    if (res === 0) {
        return 0;
    }
    return multiple - (res);
}
exports.checkLuhn = checkLuhn;
/**
 * corrected strToTest
 * @param {string} strToTest
 * @param {number} multiple
 * @return {string} valid Luhn
 */
function correctedLuhn(strToTest, multiple) {
    if (multiple === void 0) { multiple = 10; }
    var ret = checkLuhn(strToTest, multiple);
    if (ret !== 0) {
        var digit = parseInt(strToTest[(strToTest.length - 1)]);
        if ((digit + ret) > 9) {
            throw new Error("Too much number to be corrected");
        }
        var copy = strToTest.substr(0, strToTest.length - 1);
        return copy.concat((digit + ret).toString());
    }
    return strToTest;
}
exports.correctedLuhn = correctedLuhn;
/**
 * @param {string} strToTest
 * @param {number} multiple
 * @return {number[]}
 */
function computeCheckSumControl(strToTest, multiple) {
    if (multiple === void 0) { multiple = 10; }
    var ret = [];
    var i = 0;
    var checkSumTest = 0;
    var copy;
    while (i < 10) {
        copy = String(strToTest).concat(i.toString());
        checkSumTest = checkLuhn(copy, multiple) | 0;
        i += checkSumTest;
        if (i < 10) {
            ret.push(i);
            i++;
        }
    }
    return ret;
}
exports.computeCheckSumControl = computeCheckSumControl;
/**
 * Return false if strToTest is empty or checkLuhn != 0
 * @param {string} strToTest
 * @param {number} multiple
 * @return {boolean}
 */
function isLuhnValid(strToTest, multiple) {
    if (multiple === void 0) { multiple = 10; }
    if (strToTest.length === 0) {
        return false;
    }
    var ret = (checkLuhn(strToTest, multiple) === 0);
    return ret;
}
exports.isLuhnValid = isLuhnValid;
/**
 * return a clean copy of strToTest
 * @param {string} strToTest
 * @return {string}
 */
function cleanProposed(strToTest) {
    var cleanStr = String(strToTest).replace(/[^\d]/g, "");
    return cleanStr;
}
exports.cleanProposed = cleanProposed;
