(function () {
    /**
     * 用于记录日期，显示的时候根据dateObj中的日期的年月显示
     * @type {function(): {getDate: (function(): Date), setDate: setDate}}
     */
    var dateObj = (function () {
        // 默认为当前系统时间
        var _date = new Date();
        return {
            getDate: function () {
                return _date;
            },
            setDate: function (date) {
                _date = date;
            }
        };
    })();

    // 设置calendar div中的html部分
    renderHtml();
    // 表格中显示日期
    showCalendarData();
    // 绑定事件
    bindEvent();

    /**
     * 渲染html结构
     */
    function renderHtml() {
        // 获取表  <table id="calendar">
        var calendar = document.getElementById("pfo_calendar");
        // 创建tbody元素
        var calendarBody = document.createElement("tbody")

        // 设置表格去的html结构
        var _headHtml = "<tr>" +
            "<th width='130'>日</th>" +
            "<th width='130'>一</th>" +
            "<th width='130'>二</th>" +
            "<th width='130'>三</th>" +
            "<th width='130'>四</th>" +
            "<th width='130'>五</th>" +
            "<th width='130'>六</th>" +
            "</tr>";

        var _bodyHtml = "";

        var _td = "<td>" +
            "         <div class=\"date-cell\">" +
            "           <div class=\"date-day\"></div>" +
            "           <ul class='data-items'>" +
            "           </ul>" +
            "           <a class=\"interview-add js-go-add nc-req-auth\" href=\"#myModal\" data-toggle='modal'>+ 我要添加</a>" +
            "         </div>" +
            "       </td>";

        // 一个月最多31天，所以一个月最多占6行
        for (var i = 0; i < 6; i++) {
            _bodyHtml +=  "<tr>"
                          + _td
                          + _td
                          + _td
                          + _td
                          + _td
                          + _td
                          + _td
                          +  "</tr>";
        }

        // 渲染tbody
        calendarBody.innerHTML = _headHtml + _bodyHtml;
        // 添加到表格下
        calendar.appendChild(calendarBody);

    }

    /**
     * 表格中显示数据，并设置类名
     */
    function showCalendarData() {
        var _year = dateObj.getDate().getFullYear();
        var _month = dateObj.getDate().getMonth() + 1;
        var _dateStr = getDateStr(dateObj.getDate());


        // 设置顶部标题栏中的年、月信息
        var calendarTitle = document.getElementById("currentMonth");
        var titleStr = _dateStr.substr(0, 4) + "-" + _dateStr.substr(4,2);
        calendarTitle.innerHTML = titleStr;

        // 设置表格中的日期数据
        var _table = document.getElementById("pfo_calendar");
        var _tds = _table.getElementsByTagName("td");
        var _dateCell = _table.getElementsByClassName("date-cell");
        var _divs = _table.getElementsByClassName("date-day");
        var _firstDay = new Date(_year, _month - 1, 1); // 当前月第一天

        // 当天按钮
        var _today = document.getElementById("today");

        for (var i = 0; i < _divs.length; i++) {
            var _thisDay = new Date(_year, _month - 1, i + 1 - _firstDay.getDay());
            var _thisDayStr = getDateStr(_thisDay);
            _divs[i].innerText = _thisDay.getDate();
            _divs[i].setAttribute('data', _thisDayStr);
            if (_thisDayStr == getDateStr(new Date())) { // 当前天
                _divs[i].className = 'date-day currentDay';
            } else if (_thisDayStr.substr(0, 6) == getDateStr(_firstDay).substr(0, 6)) {
                _divs[i].className = 'date-day currentMonth'; // 当前月
            } else {
                _divs[i].className = 'date-day otherMonth'; // 其他月
                _divs[i].innerText = "";
                if (i == 28) {
                    console.log('28-34:otherMonth');
                    _tds[i].parentElement.style = "display: none";
                    _tds[35].parentElement.style = "display: none"
                    break;
                } else if (i == 35) {
                    console.log('35-41:otherMonth');
                    _tds[i].parentElement.style = "display: none";
                    break;
                } else {
                    _tds[i].parentElement.style = "";
                }
            }
        }


    }

    /**
     * 绑定上个月下个月事件
     */
    function bindEvent() {
        var prevMonth = document.getElementById("prevMonth")
        var nextMonth = document.getElementById("nextMonth");
        // 当天按钮
        var today = document.getElementById("today");

        addEvent(prevMonth, 'click', toPrevMonth);
        addEvent(nextMonth, 'click', toNextMonth);
        addEvent(today, 'click', toCurrentDay);
    }

    /**
     * 绑定事件
     * @param dom
     * @param eType
     * @param func
     */
    function addEvent(dom, eType, func) {
        if (dom.addEventListener) {
            dom.addEventListener(eType, function (e) {
                func(e);
            });
        } else if (dom.attachEvent) {
            dom.attachEvent('on' + eType, function(e) {
                func(e);
            });
        } else {
            dom['on' + eType] = function (e) {
                func(e);
            }
        }
    }

    /**
     * 点击上个图标触发
     */
    function toPrevMonth() {
        var date = dateObj.getDate();
        dateObj.setDate(new Date(date.getFullYear(), date.getMonth() -1, 1));
        showCalendarData();
        var _today = document.getElementById("today");
        _today.style = "visibility = visible";
    }

    /**
     * 点击下个月图标触发
     */
    function toNextMonth() {
        var date = dateObj.getDate();
        dateObj.setDate(new Date(date.getFullYear(), date.getMonth() + 1, 1));
        showCalendarData();
        var _today = document.getElementById("today");
        _today.style = "visibility: visible";
    }

    /**
     * 日期转化为字符串  yyyy-MM-dd
     * @param date
     * @returns {*}
     */
    function getDateStr(date) {
        var _year = date.getFullYear();
        var _month = date.getMonth() + 1;  // 月从0开始计数
        var _d = date.getDate();

        _month = (_month > 9) ? ("" + _month) : ("0" + _month);
        _d = (_d > 9) ? ("" + _d) : ("0" + _d);
        return _year + _month + _d;
    }

    /**
     * 点击返回到当天触发
     */
    function toCurrentDay() {
        dateObj.setDate(new Date());
        // 重新加载日历
        showCalendarData();
    }

})();