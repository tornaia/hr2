var regexIso8601 = /^(\d{4}|\+\d{6})(?:-(\d{2})(?:-(\d{2})(?:T(\d{2}):(\d{2}):(\d{2})\.(\d{1,})(Z|([\-+])(\d{2}):(\d{2}))?)?)?)?$/;

var dateFormat = 'yyyy.MM.dd';
var monthFormat = 'yyyy.MM';

function convertIso8601StringOrDateToDate(stringOrDateOrNumber) {
    if (stringOrDateOrNumber === undefined || stringOrDateOrNumber === null) {
        return stringOrDateOrNumber;
    }

    if (isDate(stringOrDateOrNumber)) {
        return stringOrDateOrNumber;
    }

    if (isNumber(stringOrDateOrNumber)) {
        return new Date(stringOrDateOrNumber);
    }

    var match = stringOrDateOrNumber.match(regexIso8601);
    var milliseconds = Date.parse(match[0]);
    if (!isNaN(milliseconds)) {
        return new Date(milliseconds);
    }

    throw 'Client side technical exception #0001!';
}

function isString(value) {
    return Object.prototype.toString.call(value) === '[object String]';
}

function isDate(value) {
    return Object.prototype.toString.call(value) === '[object Date]';
}

function isNumber(value) {
    return Object.prototype.toString.call(value) === '[object Number]';
}

function isSameDate(date1, date2) {
    if (date1 === undefined || date1 === null || date2 === undefined || date2 === null) {
        return false;
    }
    return date1.getDate() === date2.getDate() && date1.getMonth() === date2.getMonth() && date1.getFullYear() === date2.getFullYear();
}

function isSaturaday(date) {
    if (date === undefined || date === null) {
        return false;
    }
    var day = date.getDay();
    return day === 6;
}

function isSunday(date) {
    if (date === undefined || date === null) {
        return false;
    }
    var day = date.getDay();
    return day === 0;
}

function isWeekend(date) {
    if (date === undefined || date === null) {
        return false;
    }
    var day = date.getDay();
    return day === 6 || day === 0;
}

// JS implementation of org.apache.commons.lang.time.DateUtils.addMonths(Date date, int amount)
function addMonths(date, amount) {
    if (date === undefined || amount === undefined) {
        return undefined;
    }

    if (date === null || amount === null) {
        return null;
    }

    var d = addMonthsAndSetDateToTheFirstTheOfMonth(date, amount);
    var dateOfOriginalMonth = convertIso8601StringOrDateToDate(date).getDate();

    // January 0, March 2...
    if (d.getMonth() === 0 ||
            d.getMonth() === 2 ||
            d.getMonth() === 4 ||
            d.getMonth() === 6 ||
            d.getMonth() === 7 ||
            d.getMonth() === 9 ||
            d.getMonth() === 11) {
        d.setDate(dateOfOriginalMonth);
    } else if (d.getMonth() === 3 ||
            d.getMonth() === 5 ||
            d.getMonth() === 8 ||
            d.getMonth() === 10) {
        if (dateOfOriginalMonth === 31) {
            d.setDate(30);
        } else {
            d.setDate(dateOfOriginalMonth);
        }
    } else {
        // February
        if (isLeapYear(d.getFullYear())) {
            if (dateOfOriginalMonth > 29) {
                d.setDate(29);
            } else {
                d.setDate(dateOfOriginalMonth);
            }
        } else {
            if (dateOfOriginalMonth > 28) {
                d.setDate(28);
            } else {
                d.setDate(dateOfOriginalMonth);
            }
        }
    }
    return d;
}

function addMonthsAndSetDateToTheFirstTheOfMonth(date, amount) {
    var cloneDate = new Date(convertIso8601StringOrDateToDate(date).getTime());
    cloneDate.setDate(1);
    var currentMonth = cloneDate.getMonth();
    cloneDate.setMonth(currentMonth + amount);
    return cloneDate;
}

function isLeapYear(year) {
    return ((year % 4 === 0) && (year % 100 !== 0)) || (year % 400 === 0);
}

function pad(number) {
    var r = String(number);
    if (r.length === 1) {
        r = '0' + r;
    }
    return r;
}

function arrayContains(array, value) {
    for (var i=0;i<array.length;i++) {
        if (array[i] === value) {
            return true;
        }
    }
    return false;
}

function elementHasClassName(element, className) {
    return element.className && element.className.split(" ").indexOf(className) > -1;
}

function elementHasAttribute(element, attributeName) {
    return element.attributes && element.attributes[attributeName] !== undefined;
}

function elementIsInAModifiedForm(element) {
    var owner = getOwnerFormItemOrDocument(element);
    return elementHasModifiedFormItem(owner);
}

function getOwnerFormItemOrDocument(element) {
    if (element === undefined || element === null || element.parentNode === undefined) {
        return document;
    }
    if (element.tagName === 'FORM' || element.attributes !== undefined && element.attributes['ng-form'] !== undefined) {
        return element;
    }
    if (getOwnerFormItemOrDocument(element.parentNode)) {
        return getOwnerFormItemOrDocument(element.parentNode);
    }
}

function elementHasModifiedFormItem(element) {
    if (element !== undefined || element.childNodes !== undefined) {
        if (element.tagName !== 'FORM' && elementHasClassName(element, 'ng-dirty') && !elementHasAttribute(element, 'ng-dirty-ignore')) {
            return true;
        }

        for (var i=0;i<element.childNodes.length;++i) {
            var childElement = element.childNodes[i];
            if (elementHasModifiedFormItem(childElement)) {
                return true;
            }
        }
    }
    return false;
}
