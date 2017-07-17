/**
 * author zengxf
 * added 2015-05-12
 */
+function (_, $) {
    $(document).on("keydown", function (e) {
        if (e.which === 8 && !$(e.target).is("input:not([readonly]):not([type=radio]):not([type=checkbox]), textarea, [contentEditable], [contentEditable=true]")) {
            e.preventDefault();
        }
    });

    var util = _.util || {};

    _.util = $.extend(util, {
        trim: function (s) {
            return $.trim(s);
        },
        isEmpty: function (s) {
            if (s === undefined || s === null || this.trim(s) === '') return true;
            return false;
        },
        getRandomNum: function (Min, Max) {
            var Range = Max - Min,
                Rand = Math.random();
            return (Min + Math.round(Rand * Range));
        },
        generateId: function ($element, prefix) {
            var id = $element.attr('id');
            if (!id) {
                id = (prefix || '') + util.getRandomNum(1000, 9000);
                $element.attr('id', id);
            }
            return id;
        },
        json2str: function (json) {
            return JSON.stringify(json);
        },
        str2json: function (str) {
            return JSON.parse(str);
        },
        emptyFun: function () {

        },
        log: function (object) {
            console.log(JSON.stringify(object));
        },
        formatForm: function ($form) {
            var formData = {},
                o = $form.serializeArray();
            for (var i in o) {
                if (typeof (formData[o[i].name]) == 'undefined')
                    formData[o[i].name] = o[i].value;
                else {
                    formData[o[i].name] += "," + o[i].value;
                }
            }
            return formData;
        }
    });
}(window, jQuery);

+function (_, $) {


    var util = _.util || {};

    //定义事件机制的超类
    function EventControl() {
    }

    EventControl.prototype.getElement = function () {
        return this.$element;
    };

    EventControl.prototype.getNamespace = function () {
        return '';
    };

    EventControl.prototype.on = function (type, data, callback) {
        var $element = this.getElement(), ns;
        if (!$element || !type) return;

        if (arguments.length == 2) {
            callback = data;
            data = undefined;
        }

        if (typeof(callback) != 'function') return;

        ns = this.getNamespace();
        $element.on(type + (ns ? ('.' + ns) : ''), data, callback);
    };

    EventControl.prototype.one = function (type, data, callback) {
        var $element = this.getElement(), ns;
        if (!$element || !type) return;

        if (arguments.length == 2) {
            callback = data;
            data = undefined;
        }

        if (typeof(callback) != 'function') return;

        ns = this.getNamespace();
        $element.one(type + (ns ? ('.' + ns) : ''), data, callback);
    };

    EventControl.prototype.off = function (type) {
        var $element = this.getElement(), ns;
        if (!$element || !type) return;

        ns = this.getNamespace();
        $element.off(type + (ns ? ('.' + ns) : ''));
    };

    EventControl.prototype.trigger = function (type, data) {
        var $element = this.getElement(), e, ns;
        if (!$element || !type) return;

        ns = this.getNamespace();
        e = $.Event(type + (ns ? ('.' + ns) : ''));
        $element.trigger(e, data);
        return !e.isDefaultPrevented();
    };

    _.util = $.extend(util, {
        eventControl: EventControl
    });

}(window, jQuery);

+function (_, $) {


    var util = _.util || {};

    //定义表单控件的超类
    function FormControl(element, options) {
        this.$element = $(element);
        this.options = this.getOptions(options);
        init.apply(this);
    }

    FormControl.DEFAULTS = {
        name: '',
        type: '',
        value: '',
        defaultValue: '',
        mode: 1,
        needPost: true,
        onBeforeChange: $.noop,
        onChange: $.noop
    };

    FormControl.getNamespace = function () {
        return '';
    };

    FormControl.prototype = $.extend({}, util.eventControl.prototype);
    FormControl.prototype.constructor = FormControl;

    FormControl.prototype.getDefaults = function () {
        return FormControl.DEFAULTS;
    };

    FormControl.prototype.getOptions = function (options) {
        var defaults = this.getDefaults(),
            opts = $.extend({}, defaults, options || {}, this.$element.data()),
            obj = {};

        for (var i in defaults) {
            obj[i] = opts[i];
        }

        return obj;
    };

    function init() {
        var opts = this.options;
        this.name = this.getName();
        this.initValue = this.getInitValue();
        typeof(this.initValue) == 'object' && (this.initValue = JSON.stringify(this.initValue));
        this.mode = opts.mode;
        this.type = opts.type;
        this.ns = this.constructor.getNamespace();

        delete opts.type;
        delete opts.name;
        delete opts.value;
        delete opts.defaultValue;

        bindEvents.apply(this);
    }

    function bindEvents() {
        this.on('inited', $.proxy(afterInit, this));
    }

    function afterInit() {
        if (this.mode === 3) {
            this.disable();
        }
    }

    FormControl.prototype.getName = function () {
        var $element = this.$element,
            opts = this.options;
        return opts.name || $element.attr('name') || $element.data('name') || '';
    };

    FormControl.prototype.getInitValue = function () {
        var $element = this.$element,
            opts = this.options;
        if (opts.mode === 1) {
            return opts.defaultValue;
        } else {
            return util.isEmpty(opts.value) ?
                (('val' in $element) && $element.val()) :
                opts.value;
        }
    };

    FormControl.prototype.ifNeedPost = function () {
        return this.options.needPost;
    };

    FormControl.prototype.setNeedPost = function (val) {
        val = !!val;
        this.options.needPost = val;
    };

    FormControl.prototype.getNamespace = function () {
        return this.ns;
    };

    FormControl.prototype.setValue = function (value) {
        console.log('setValue方法未实现');
    };

    FormControl.prototype.getValue = function () {
        console.log('getValue方法未实现');
    };

    FormControl.prototype.disable = function () {
        console.log('disable方法未实现');
    };

    FormControl.prototype.enable = function () {
        console.log('enable方法未实现');
    };

    FormControl.prototype.reset = function () {
        console.log('reset方法未实现');
    };

    _.util = $.extend(util, {
        formControl: FormControl
    });

}(window, jQuery);

+function (_, $) {


    var util = _.util || {};
    //定义权限资源的超类
    function ResourceControl() {
    }

    ResourceControl.prototype = $.extend({}, util.eventControl.prototype);
    ResourceControl.prototype.constructor = ResourceControl;

    ResourceControl.prototype.addToPermission = function (resourceType) {

    };

    ResourceControl.prototype.isValidResourceControl = function () {
        return this.getElement().is(':visible');
    };

    ResourceControl.prototype.isAjaxLoaded = function () {
        return this.ajaxLoadStatus === true;
    };

    ResourceControl.prototype.setAjaxLoadStatus = function () {
        return this.ajaxLoadStatus = true;
    };

    ResourceControl.prototype.resetAjaxLoadStatus = function () {
        return this.ajaxLoadStatus = false;
    };

    ResourceControl.prototype.getResourceId = function () {

    };

    _.util = $.extend(util, {
        resourceControl: ResourceControl
    });

}(window, jQuery);

/**
 * author zengxf
 * added 2015-05-19
 * 好的代码都遵循单var的规则
 */
+function (_, $) {
    var util = _.util || {}, $loading;

    +function () {
        $loading = $('<div class="loading" style="display:none">正在处理 <img src="images/_base/loading.gif" /></div>');
        $loading.appendTo($('body'));

        $(document).ajaxSend(function () {
            $loading.show();
        }).ajaxComplete(function (e, req) {
            $loading.hide();
            /*if(req.status ==0 ) {
                try {
                    var win = _,
                        top = win.parent;
                    while(top != win) {
                        win = top;
                        top = win.parent;
                    }
                    (top.contentWindow || top)['util'].url.go("logout", true);
                }catch(e) {
                    util.info('请重新登录！');
                }

            }*/
        });
    }();

    _.util = $.extend(util, {
        ajax: function (stype) {

            var dataTypes = {
                    json: 1,
                    html: 1,
                    xml: 1,
                    text: 1,
                    script: 1
                },
                callbacks = {
                    success: 'done',
                    error: 'fail',
                    complete: 'always'
                },
                dataType = dataType in dataTypes ? dataType : 'json',
                inited = false,
                _ajax = function (_url, _method, _data, _async, _dataType) {
                    //已调用过此方法不能再次调用
                    if (inited) return;
                    inited = true;

                    //添加随机数
                    if (_url.indexOf('?') > -1) {
                        _url = _url + '&rnd=' + Math.random();
                    } else {
                        _url = _url + '?rnd=' + Math.random();
                    }
                    //为请求添加ajax标识，方便后台区分ajax和非ajax请求
                    _url += '&_ajax=true';

                    this.defReq = $.when($.ajax({
                        url: _url,
                        dataType: _dataType,
                        async: _async,
                        method: _method,
                        data: _data
                    }));

                    return this;
                },
                _addCallback = function (call, callback) {
                    //未调用_ajax前，不允许调用此方法
                    if (!inited) return this;

                    var _this = this;
                    if (typeof(callback) === 'function' && this.defReq) {
                        this.defReq[call](function () {
                            callback.apply(_this, arguments);
                        });
                    }
                    return this;
                },
                obj = {
                    Constructor: util.ajax,
                    html: function (url, data) {
                        return _ajax.call(this, url, 'GET', data, true, 'html');
                    },
                    get: function (url, data) {
                        return _ajax.call(this, url, 'GET', data, true, dataType);
                    },
                    post: function (url, data) {
                        return _ajax.call(this, url, 'POST', data, true, dataType);
                    },
                    syncGet: function (url, data) {
                        return _ajax.call(this, url, 'GET', data, false, dataType);
                    },
                    syncPost: function (url, data) {
                        return _ajax.call(this, url, 'POST', data, false, dataType);
                    }
                };

            // success/error/complete callback api
            for (var i in callbacks) {
                obj[i] = (function (i) {
                    return function (callback) {
                        return _addCallback.call(this, callbacks[i], callback);
                    }
                })(i);
            }

            return obj;
        }
    });

    _.util.loading = $loading;
}(window, jQuery);
/**
 * @author 曾险峰
 * 2015-08-20
 */

+function (_, $) {


    var ns = 'util.alert',
        DEFAULTS = {
            width: 400,
            height: 200
        },
        util = _.util || {},
        _alert;

    ///private functions
    function getDefaults() {
        return DEFAULTS;
    }

    function getTemplate() {
        return ['<div class="modal modal-alert fade" data-backdrop="false" data-show="false" data-keyboard="false" role="dialog">',
            '  <div class="modal-dialog">',
            '    <div class="modal-content">',
            '      <div class="modal-header">',
            '        <h4 class="modal-title">操作提示</h4>',
            '      </div>',
            '      <div class="modal-body"></div>',
            '      <div class="modal-footer">',
            '        <button type="button" class="btn btn-primary btn-ok">确定</button>',
            '      </div>',
            '    </div>',
            '  </div>',
            '</div>'].join("");
    }

    function init() {
        var $modal = this.$modal;
        this.$modalBody = $modal.find('.modal-body');
        this.$dialog = $modal.find('.modal-dialog');
        $modal.modal();
        reset.apply(this);
    }

    function reset() {
        this.callback = function () {
        };
        this.setBtnText('确定');
        setPosition.call(this, this.options.height);
    }

    function adjustPosition($modal) {
        var $dialog = this.$dialog,
            height = $dialog.outerHeight();

        $modal.addClass('visible');
        setPosition.call(this, height);
        $modal.removeClass('visible');
    }

    function setPosition(height) {
        var opts = this.options,
            width = opts.width,
            $dialog = this.$dialog;

        $dialog.css({
            'width': width + 'px',
            'height': height + 'px',
            'margin-left': '-' + (width / 2) + 'px',
            'margin-top': '-' + (height / 2) + 'px'
        });
    }

    function bindEvents() {
        this.$modal.on('click.' + ns, '.btn-ok', $.proxy(ok, this));
    }

    function ok(e) {
        var callback = this.callback;
        if (!(typeof(callback) == 'function' && callback() === false)) {
            this.hide();
        }
    }

    //class definition
    function Alert() {
        this.$modal = $(getTemplate());
        this.$modal.appendTo($('body'));
        this.options = getDefaults();

        init.apply(this);
        bindEvents.apply(this);
    }

    Alert.prototype.show = function (message, callback) {
        var $modal = this.$modal,
            $modalBody = this.$modalBody;

        if (typeof(callback) == 'function') {
            this.callback = callback;
        }

        adjustPosition.call(this, $modal);

        $modalBody.text(message);
        util.mask.show();
        $modal.modal('show');
    };

    Alert.prototype.setBtnText = function (text) {
        var $modal = this.$modal;
        text && $modal.find('.btn-ok').text(text);
    };

    Alert.prototype.hide = function () {
        var $modal = this.$modal,
            $modalBody = this.$modalBody,
            that = this;

        $modalBody.text('');
        $modal.modal('hide');
        $modal.one('hidden.bs.modal', function () {
            util.mask.hide();
            reset.apply(that);
        });
    };

    //api definition
    _alert = new Alert();

    _.util = $.extend(util, {
        alert: function (message, callback) {
            _alert.show(message, callback);
        }
    });

    util.alert.instance = _alert;

}(window, jQuery);
/**
 * author zengxf
 * added 2015-05-16
 */
;
(function (_, $) {


    var ns = 'mam.detect',
        session = _.sessionStorage,
        local = _.localStorage,
        key = 'browser-detect-display',
        duration = 400;

    function ifDisplay() {
        var sessionFlag = session.getItem(key),
            localFlag = local.getItem(key);

        if (sessionFlag === 'no' || localFlag === 'no') {
            return false;
        }
        return true;
    }

    function Detect($element) {
        this.$element = $element;
        ifDisplay() && this.init();
    }

    Detect.prototype = {
        constructor: Detect,
        init: function () {
            var $element = this.$element;

            $element.addClass('active');
            $element[0].offsetWidth;
            $element.addClass('slideUp');

            $element.one('bsTransitionEnd', $.proxy(this.bindEvents, this)).emulateTransitionEnd(duration);
        },
        bindEvents: function() {
            var $element = this.$element;

            $element.on('click.' + ns, 'button', function () {
                var $btn = $(this);
                if ($btn.hasClass('btn-close')) {
                    session.setItem(key, 'no');
                } else {
                    local.setItem(key, 'no');
                }

                $element.off('click');
                $element.removeClass('slideUp');

                $element.one('bsTransitionEnd', function () {
                    $element.removeClass('active');
                }).emulateTransitionEnd(duration);
            });
        }
    };

    function Plugin() {
        return this.each(function () {
            var $this = $(this),
                data = $this.data(ns);

            if (!data) $this.data(ns, (data = new Detect($this)));
        });
    }

    var old = $.fn.detect;

    $.fn.detect = Plugin;
    $.fn.detect.Constructor = Detect;

    $.fn.detect.noConflict = function () {
        $.fn.detect = old;
        return this;
    }

})(window, jQuery);

/**
 * @author 曾险峰
 * 2015-08-20
 */

+function (_, $) {


    var ns = 'util.button',
        DEFAULTS = {
            onClick: undefined
        },
        util = _.util || {},
        _button;

    //private functions
    function getDefaults() {
        return DEFAULTS;
    }

    function getOptions(options) {
        var opts = $.extend({}, getDefaults(), options);
        return opts;
    }

    function bindEvents() {
        this.$element.on('click.' + ns, $.proxy(click, this));
    }

    function click(e) {
        var $element = this.$element;
        if ($element.hasClass('disabled')) return;//防重复点击
        e.preventDefault();//阻止链接跳转和表单提交

        $element.button('loading');

        var opts = this.options;
        var args = arguments;
        var that = this;
        setTimeout(function () {
            if (typeof(opts.onClick) === 'function') {
                var retValue = opts.onClick.apply(null, args);
                if (retValue && retValue.Constructor === util.ajax) {
                    retValue.complete(function () {
                        reset.apply(that);
                    });
                } else if (retValue !== false) {
                    reset.apply(that);
                }
            } else {
                reset.apply(that);
            }
        }, 0);
    }

    function reset() {
        var $element = this.$element;
        $element.button('reset').blur();
        setTimeout(function () {
            if ($element.hasClass('ub-disable')) {
                _button.disable($element[0]);
            }
        }, 0);
    }

    //class definition
    function Button(element, options) {
        this.$element = $(element);
        this.options = getOptions(options);
        bindEvents.apply(this);
    }

    Button.prototype.setOptions = function (options) {
        var opts = $.extend({}, this.options, options);
        this.options = opts;
    };

    //api
    _button = function (selector, option) {
        var obj = [];

        $(selector).each(function () {
            var $this = $(this),
                data = $this.data('util.button'),
                options = typeof option == 'object' && option;

            if (!data) $this.data('util.button', (data = new Button(this, options)));
            else data.setOptions(options);

            obj.push(data);
        });

        if (obj.length == 1) return obj[0];
        return obj;
    };

    $.extend(_button, {
            disable: function (selector) {
                $(selector).each(function () {
                    var $this = $(this),
                        tagName = $this[0].tagName.toLowerCase();

                    $this.addClass('disabled').addClass('ub-disable');
                    if (tagName === 'a') {
                        $this.attr('disabled', 'disabled');
                    } else if (tagName === 'button') {
                        $this.prop('disabled', true);
                    }
                });
            }
            ,
            enable: function (selector) {
                $(selector).each(function () {
                    var $this = $(this),
                        tagName = $this[0].tagName.toLowerCase();

                    $this.removeClass('disabled').removeClass('ub-disable');
                    if (tagName === 'a') {
                        $this.removeAttr('disabled');
                    } else if (tagName === 'button') {
                        $this.prop('disabled', false);
                    }
                });
            }
        }
    );

    _.util = $.extend(util, {
        button: _button
    });

}(window, jQuery);
/**
 * @author 曾险峰
 * 2015-08-20
 */

+function (_, $) {


    var ns = 'mam.confirm';

    function Confirm() {
        this.init();
        this.bindEvents();
    }

    Confirm.DEFAULTS = {
        width: 400,
        height: 200
    };

    Confirm.prototype.getDefaults = function () {
        return Confirm.DEFAULTS;
    };

    Confirm.prototype.init = function () {
        this.$body = $(document.body);
        this.$modal = $(_getTemplate());
        this.options = this.getDefaults();

        var $modal = this.$modal;
        var $body = this.$body;
        $modal.appendTo($body);// add to dom

        this.$modalBody = $modal.find('.modal-body');
        this.$dialog = $modal.find('.modal-dialog');

        $modal.modal();

        this.reset();

        function _getTemplate() {
            return ['<div class="modal modal-confirm fade" data-backdrop="false" data-show="false" data-keyboard="false" role="dialog">',
                '  <div class="modal-dialog">',
                '    <div class="modal-content">',
                '      <div class="modal-header">',
                '        <h4 class="modal-title">操作提示</h4>',
                '      </div>',
                '      <div class="modal-body"></div>',
                '      <div class="modal-footer">',
                '        <button type="button" class="btn btn-primary btn-ok">确定</button>',
                '        <button type="button" class="btn btn-default btn-cancel">取消</button>',
                '      </div>',
                '    </div>',
                '  </div>',
                '</div>'].join("");
        }
    };

    Confirm.prototype.adjustPosition = function ($modal) {
        $modal.addClass('visible');
        var $dialog = this.$dialog;
        var height = $dialog.outerHeight();
        this.setPosition(height);
        $modal.removeClass('visible');
    };

    Confirm.prototype.resetPosition = function (height) {
        this.setPosition(this.options.height);
    };

    Confirm.prototype.setPosition = function (height) {
        var opts = this.options;
        var width = opts.width;
        var $dialog = this.$dialog;
        $dialog.css({
            'width': width + 'px',
            'height': height + 'px',
            'margin-left': '-' + (width / 2) + 'px',
            'margin-top': '-' + (height / 2) + 'px'
        });
    };

    Confirm.prototype.reset = function () {
        this.okCallback = this.cancelCallback = function () {
        };
        this.resetPosition();
    };

    Confirm.prototype.bindEvents = function () {
        this.$modal.on('click.' + ns, '.btn-ok', $.proxy(this.ok, this));
        this.$modal.on('click.' + ns, '.btn-cancel', $.proxy(this.cancel, this));
    };

    Confirm.prototype.ok = function (e) {
        var okCallback = this.okCallback;
        if (!(typeof(okCallback) == 'function' && okCallback() === false)) {
            this.hide();
        }
    };

    Confirm.prototype.cancel = function (e) {
        var cancelCallback = this.cancelCallback;
        if (!(typeof(cancelCallback) == 'function' && cancelCallback() === false)) {
            this.hide();
        }
    };

    Confirm.prototype.show = function (message, okCallback, cancelCallback) {
        if (typeof(okCallback) == 'function') {
            this.okCallback = okCallback;
        }
        if (typeof(cancelCallback) == 'function') {
            this.cancelCallback = cancelCallback;
        }

        var $modal = this.$modal;
        var $modalBody = this.$modalBody;

        this.adjustPosition($modal);

        $modalBody.html(message);
        util.mask.show();
        $modal.modal('show');
    };

    Confirm.prototype.hide = function () {
        var $modal = this.$modal;
        var $modalBody = this.$modalBody;
        var that = this;

        $modalBody.text('');
        $modal.modal('hide');
        $modal.one('hidden.bs.modal', function () {
            util.mask.hide();
            that.reset();
        });
    };

    var util = _.util || {};

    var confirm = new Confirm();

    var _confirm = function (message, ok, cancel) {
        confirm.show(message, ok, cancel);
    };

    _confirm.delete = function (ok, cancel) {
        confirm.show('确定要删除吗？', ok, cancel);
    };

    _confirm.operate = function (ok, cancel) {
        confirm.show('确定要执行该操作吗？', ok, cancel);
    };

    _.util = $.extend(util, {
        confirm: _confirm
    });


}(window, jQuery);
/**
 * author zengxf
 * added 2015-05-16
 */
;
(function (_, $) {
    var util = _.util || {};

    _.util = $.extend(util, {
        data: (function () {
            var _delete = function (resourceType, ids, callback) {

                util.confirm.delete(function () {
                    util.ajax().post('data/edit/' + resourceType + '/deletes', {
                        ids: JSON.stringify(ids)
                    }).success(function (res) {
                        if (res.code == 200) {
                            callback(res);
                        } else if (res.code == 201) {
                            util.alert(res.message);
                        } else {
                            util.info('操作失败，请稍后再试！');
                        }
                    }).error(function () {
                        util.info('操作失败，请稍后再试！');
                    });
                });
            };

            return {
                'delete': _delete
            }
        })()
    })
})(window, jQuery);

;(function($, window, document,_u){
    if(!window.browser){

        var userAgent = navigator.userAgent.toLowerCase(),uaMatch;
        window.browser = {}

        /**
         * 判断是否为ie
         */
        function isIE(){
            return ("ActiveXObject" in window);
        }
        /**
         * 判断是否为谷歌浏览器
         */
        if(!uaMatch){
            uaMatch = userAgent.match(/chrome\/([\d.]+)/);
            if(uaMatch!=null){
                window.browser['name'] = 'chrome';
                window.browser['version'] = uaMatch[1];
            }
        }
        /**
         * 判断是否为火狐浏览器
         */
        if(!uaMatch){
            uaMatch = userAgent.match(/firefox\/([\d.]+)/);
            if(uaMatch!=null){
                window.browser['name'] = 'firefox';
                window.browser['version'] = uaMatch[1];
            }
        }
        /**
         * 判断是否为opera浏览器
         */
        if(!uaMatch){
            uaMatch = userAgent.match(/opera.([\d.]+)/);
            if(uaMatch!=null){
                window.browser['name'] = 'opera';
                window.browser['version'] = uaMatch[1];
            }
        }
        /**
         * 判断是否为Safari浏览器
         */
        if(!uaMatch){
            uaMatch = userAgent.match(/safari\/([\d.]+)/);
            if(uaMatch!=null){
                window.browser['name'] = 'safari';
                window.browser['version'] = uaMatch[1];
            }
        }
        /**
         * 最后判断是否为IE
         */
        if(!uaMatch){
            if(userAgent.match(/msie ([\d.]+)/)!=null){
                uaMatch = userAgent.match(/msie ([\d.]+)/);
                window.browser['name'] = 'ie';
                window.browser['version'] = uaMatch[1];
            }else{
                /**
                 * IE10
                 */
                if(isIE() && !!document.attachEvent && (function(){"use strict";return !this;}())){
                    window.browser['name'] = 'ie';
                    window.browser['version'] = '10';
                }
                /**
                 * IE11
                 */
                if(isIE() && !document.attachEvent){
                    window.browser['name'] = 'ie';
                    window.browser['version'] = '11';
                }
            }
        }

        /**
         * 注册判断方法
         */
        if(!$.isIE){
            $.extend({
                isIE:function(){
                    return (window.browser.name == 'ie');
                }
            });
        }
        if(!$.isChrome){
            $.extend({
                isChrome:function(){
                    return (window.browser.name == 'chrome');
                }
            });
        }
        if(!$.isFirefox){
            $.extend({
                isFirefox:function(){
                    return (window.browser.name == 'firefox');
                }
            });
        }
        if(!$.isOpera){
            $.extend({
                isOpera:function(){
                    return (window.browser.name == 'opera');
                }
            });
        }
        if(!$.isSafari){
            $.extend({
                isSafari:function(){
                    return (window.browser.name == 'safari');
                }
            });
        }
        if(!$.isBrowsers){
            $.extend({
                isBrowsers:function(browsers){
                    if(!$.isArray(browsers)) return false;

                    for(var l = browsers.length - 1; l >= 0; l--) {
                        if(window.browser.name == browsers[l]) {
                            return true;
                        }
                    }
                    return false;
                }
            });
        }
    }
})(jQuery, window, document, _);
/**
 * @author 曾险峰
 * 2015-08-20
 */

+function (_, $) {


    var ns = 'util.form',
        util = _.util || {},
        $document = $(document),
        $body = $(document.body),
        objects = {};

    function Form(element, options) {
        var $element = $(element);

        this.$element = $element;
        this.options = this.getOptions(options);
        this.subscribeEvents();//订阅options中的事件
        this.init();
    }

    Form.DEFAULTS = {
        mode: 1,
        postUrl: '',
        putUrl: '',
        datatable: '',
        resourceType: '',
        type: 'edit',
        fieldOption: {},
        onBeforeSave: $.noop,
        onAfterSave: $.noop
    };

    Form.prototype = $.extend({}, util.eventControl.prototype);
    Form.prototype.constructor = Form;

    // override eventControl methods
    Form.prototype.getElement = function () {
        return this.$element;
    };

    Form.prototype.getNamespace = function () {
        return ns;
    };

    Form.prototype.getDefaults = function () {
        return Form.DEFAULTS;
    };

    Form.prototype.getOptions = function (options) {
        var opts = $.extend({}, this.getDefaults(), this.$element.data(), options);
        return opts;
    };

    Form.prototype.getMode = function () {
        return this.options.mode;
    };

    Form.prototype.subscribeEvents = function () {
        var opts = this.options;

        $document.on('keydown.submit.' + ns, function (e) {
            var isTextArea = e.target.tagName.toLowerCase() === 'textarea';
            if (!isTextArea && e.which == 13) {
                e.preventDefault();
            }
        });

        util.button('button[name=btn-default-save]', {
            onClick: $.proxy(this.save, this)
        });

        util.button('button[name=btn-default-back]', {
            onClick: $.proxy(back, this)
        });

        if (typeof(opts.onBeforeSave) === 'function') {
            this.on('beforeSave', opts.onBeforeSave);
        }

        if (typeof(opts.onAfterSave) === 'function') {
            this.on('afterSave', opts.onAfterSave);
        }
    };

    Form.prototype.init = function () {
        var opts = this.options;
        if (!opts.mode) opts.mode = 1;
        initMode.apply(this);
        this.fieldMap = FieldMap(this);
        this.validation = Validation(this);
        setupTooltip.apply(this);
    };

    Form.prototype.reset = function () {
        this.fieldMap.reset();
        this.validation.reset();
    };

    Form.prototype.getFormData = function () {
        return this.fieldMap.getData();
    };

    Form.prototype.resolveFormData = function (data) {
        return this.fieldMap.resolveData(data);
    };

    Form.prototype.save = function (e) {
        var that = this,
            formData,
            postData,
            url;

        if (!this.validation.validate()) return true;

        formData = this.getFormData();

        if (!this.trigger('beforeSave', formData)) return true;

        url = getUrl(that);
        util.loading.show();

        postData = this.resolveFormData(formData);

        return util.ajax().post(url, {
            data: JSON.stringify(postData)
        }).success(function (res) {
            _restoreForm(formData);//form data可能在beforeSave中被修改，所以需要根据最新的form data设置字段的value

            if (res.code == 201) {
                util.alert(res.message);
                return;
            } else if (res.code == 202) {
                util.info('保存成功！');
                _afterSave(res.data);//保存成功后根据后台返回的地址跳转
                return;
            } else if (res.code == 200) {
                util.info('保存成功！');
                if (!that.trigger('afterSave', res)) {
                    return;
                }
                _afterSave();
            } else {
                util.info('保存失败！');
            }
        }).error(function () {
            util.info('保存失败！');
            _restoreForm();
        });

        function _afterSave(url) {
            setTimeout(function () {
                back.call(that, url);
            }, 1200);
        }

        function _restoreForm(data) {
            that.validation.reset();
            data && that.fieldMap.setData(data);
        }
    };

    function initMode() {
        var opts = this.options;
        if (opts.mode === 3) {
            util.button.disable('.menu-container .btn:not([name="btn-default-back"])');
        }
    }

    function setupTooltip() {
        this.$element.find('[data-toggle="tooltip"]').tooltip();
    }

    function FieldMap(that) {
        var opts = that.options,
            fieldOptions = opts.fieldOption,
            fieldType = {
                text: util.formControl.text,
                number: util.formControl.number,
                date: util.formControl.date,
                select: util.formControl.select,
                checkbox: util.formControl.checkbox,
                radio: util.formControl.radio,
                combotree: util.formControl.combotree,
                lookup: util.formControl.lookup,
                ueditor: util.formControl.ueditor,
                upload: util.formControl.upload
            },
            fieldSelector = '.form-field',
            dataFilter = 'appForm-',
            map = {};

        (function () {
            that.$element.find(fieldSelector).each(function () {
                var $this = $(this),
                    type = $this.data('type'),
                    control,
                    fieldName,
                    name;

                if (!(type in fieldType)) return;

                control = fieldType[type](this, $.extend({
                    mode: opts.mode
                }, fieldOptions[$this.attr('name') || $this.data('name')] || {}));

                name = control.name;
                map[name] = {
                    control: control
                };

                if (name in that) {
                    fieldName = 'appForm_' + name;
                } else {
                    fieldName = name;
                }

                map[name].fieldName = fieldName;
                that[fieldName] = control;
            });
        })();

        return {
            getData: _getData,
            setData: _setData,
            resolveData: _resolveData,
            reset: _reset
        };

        function _reset() {
            var data = {}, control;
            for (var i in map) {
                map[i].control.reset();
            }

            return data;
        }

        function _getData() {
            var data = {}, control;
            for (var i in map) {
                if (i.indexOf(dataFilter) === 0) continue;

                control = map[i].control;
                data[i] = control.getValue();

                if (control.isMultiControl && control.isMultiControl()) {
                    $.extend(data, control.getExtraValue());
                }
            }

            return data;
        }

        function _setData(data) {
            if (!(data && typeof(data) === 'object')) return;
            var control;
            for (var i in map) {
                if (i.indexOf(dataFilter) === 0) continue;

                control = map[i].control;

                if (i in data) {
                    if (control.type === 'lookup') {
                        control.setValue(data[i], data[control.getTextName()]);
                    } else {
                        control.setValue(data[i]);
                    }
                }
            }
        }

        function _resolveData(data) {
            if (!(data && typeof(data) === 'object')) return;
            var control,
                data = $.extend({}, data);
            for (var i in map) {
                if (i.indexOf(dataFilter) === 0) continue;

                control = map[i].control;

                if (i in data) {
                    if (!control.ifNeedPost()) {
                        data[i] = '';
                    }
                }
            }

            return data;
        }
    }

    function Validation(that) {
        var opts = that.options,
            $element = that.$element,
            validation;

        $element.formValidation($.extend({}, util.config.formValidation, opts.type === 'query' ? {
            row: {
                selector: '.col',
                valid: 'has-success',
                invalid: 'has-error'
            }
        } : {}));

        validation = $element.data('formValidation');

        function _removeField(name) {
            validation.resetField(name);
            validation.removeField(name);
        }

        function _addField(name, options) {
            validation.addField(name, options);
        }

        function _reset(name) {
            if (name) {
                validation.resetField(name);
            } else {
                validation.resetForm();
            }
        }

        function _addValidator(name, validator, options) {
            options.message && validation.updateMessage(name, validator, options.message);
            validation.addValidator(name, validator, options);
        }

        function _updateValidator(name, options) {
            validation.updateOption(name, options.validator, options.option, options.value);
        }

        function _removeValidator(name, validator) {
            validation.updateStatus(name, 'NOT_VALIDATED', validator);
            validation.removeValidator(name, validator);
        }

        function _validate(name) {
            if (name) {
                validation.validateField(name);
                return validation.isValidField(name);
            } else {
                validation.validate();
                return validation.isValid();
            }
        }

        return {
            validate: _validate,
            addValidator: _addValidator,
            updateValidator: _updateValidator,
            removeValidator: _removeValidator,
            addField: _addField,
            removeField: _removeField,
            reset: _reset
        }
    }

    function back(e) {
        util.url.back((typeof(e) === 'string' && e) || '', this.options.datatable || '');
    }

    function getUrl(that) {
        var url,
            opts = that.options,
            resourceType = opts.resourceType,
            mode = opts.mode;

        if (resourceType) {
            if (mode == 1) {
                url = util.url.postUrl(resourceType);
            } else {
                url = util.url.putUrl(resourceType);
            }
        }

        if (opts.postUrl && mode == 1) {
            url = opts.postUrl;
        }

        if (opts.putUrl && mode != 1) {
            url = opts.putUrl;
        }
        return url;
    }

    var _form = function (selector, option) {
        var obj = [];

        $(selector).each(function () {
            var that = $(this);
            var data = that.data(ns);
            var options = typeof option == 'object' && option;

            var id = util.generateId(that, 'appForm-');
            if (!data) {
                data = new Form(this, options);
                that.data(ns, data);

                objects[id] = data;
            }
            obj.push(data);
        });

        if (obj.length == 1) return obj[0];
        return obj;
    };

    $.extend(_form, {
        getForms: function () {
            return objects;
        },
        get: function (id) {
            if (!id) {
                for (var i in objects) {
                    if (objects.hasOwnProperty(i)) {
                        return objects[i];
                    }
                }
            }
            return objects[id];
        },
        remove: function (id) {
            delete objects[id];
        },
        each: function (callback) {
            var t;
            for (var i in objects) {
                if (!objects.hasOwnProperty(i)) {
                    continue;
                }
                t = objects[i];
                callback(t);
            }
        }
    });

    _.util = $.extend(util, {
        form: _form
    });


}(window, jQuery);
/**
 * author zengxf
 * added 2015-09-09
 */
+function (_, $) {


    var util = _.util || {},
        formControl = util.formControl = util.formControl || {},
        _checkbox;

    function bindEvents() {
        var opts = this.options,
            that = this,
            $element = this.$element;

        $element.on('change.' + Checkbox.getNamespace(), getInputSelector.apply(this), function (e) {
            var val = that.getValue();

            if (!that.trigger('beforeChange', val)) {
                setInputValue.call(that, that.lastValue);
                return;
            }
            that.lastValue = val;
            that.trigger('changed', val);
        });

        if (typeof(opts.onChange) === 'function') {
            this.on('changed', opts.onChange);
        }

        if (typeof(opts.onBeforeChange) === 'function') {
            this.on('beforeChange', opts.onBeforeChange);
        }
    }

    function setInputValue(value) {
        this.$inputs.val(value.split(','));
    }

    function setInitValue(value) {
        setInputValue.call(this, value);
        this.lastValue = value;
    }

    function init() {
        var $element = this.$element, $inputs;

        bindEvents.apply(this);


        $inputs = $element.find(getInputSelector.apply(this));
        this.$inputs = $inputs;
        $inputs.prop('name', this.name);
        setInitValue.call(this, this.initValue);
        this.trigger('inited');
    }

    function getInputSelector() {
        return 'input[type=checkbox]';
    }

    function Checkbox(element, options) {
        formControl.apply(this, arguments);
        init.apply(this);
    }

    Checkbox.getNamespace = function () {
        return 'util.formControl.checkbox';
    };

    Checkbox.DEFAULTS = $.extend({}, formControl.prototype.constructor.DEFAULTS, {});

    Checkbox.prototype = $.extend({}, formControl.prototype);
    Checkbox.prototype.constructor = Checkbox;

    Checkbox.prototype.getDefaults = function () {
        return Checkbox.DEFAULTS;
    };

    Checkbox.prototype.setValue = function (value) {
        var $inputs = this.$inputs;

        value = util.isEmpty(value) ? '' : (value + '');
        if (value === this.getValue()) return;

        setInputValue.call(this, value);
        $inputs.eq(0).trigger('change');
    };

    Checkbox.prototype.getValue = function () {
        var val = [];
        this.$inputs.filter(':checked').each(function () {
            val.push($(this).prop('value'));
        });
        return val.join(',');
    };

    Checkbox.prototype.disable = function () {
        var $element = this.$element;
        $element.addClass('disabled');
        this.$inputs.prop('disabled', true);
    };

    Checkbox.prototype.enable = function () {
        var $element = this.$element;
        $element.removeClass('disabled');
        this.$inputs.prop('disabled', false);
    };

    Checkbox.prototype.reset = function () {
        setInputValue.call(this, this.initValue);
    };

    _checkbox = function (selector, option) {
        var obj = [],
            ns = Checkbox.getNamespace();

        $(selector).each(function () {
            var $this = $(this),

                data = $this.data(ns),
                options = typeof option == 'object' && option;

            if (!data) $this.data(ns, (data = new Checkbox(this, options)));

            obj.push(data);
        });

        if (obj.length == 1) return obj[0];
        return obj;
    };

    $.extend(formControl, {
        checkbox: _checkbox
    });

}(window, jQuery);
/**
 * author zengxf
 * added 2015-09-09
 */
+function (_, $) {


    var util = _.util || {},
        formControl = util.formControl = util.formControl || {},
        _combotree;

    //off toggle
    function offClickEvent() {
        var $element = this.$element;
        $element.off('click.' + Combotree.getNamespace());
    }

    //toggle when click
    function subscribeClickEvent() {
        var that = this,
            $element = this.$element;
        $element.on('click.' + Combotree.getNamespace(), '.input-group', $.proxy(that.toggle, that));
    }

    function bindEvents() {
        var opts = this.options,
            that = this,
            $element = this.$element,
            $input = this.$input,
            ns = Combotree.getNamespace();

        subscribeClickEvent.apply(this);

        //hide when click document
        $element.on('mouseenter.' + ns, function (e) {
            $(document).off('click.' + ns);
        }).on('mouseleave.' + ns, function (e) {
            $(document).on('click.' + ns, clearComboTree);
        });

        this.on('treeValueChanged', function (e, obj) {
            var value = obj.value,
                text = obj.text;
            if (that.lastValue !== value) {
                that.value = value;
                $input.val(text);
                $input.trigger('change');
            }
        });

        $element.on('change.' + ns, '.combotree-input', function (e) {
            var text = that.getText(),
                val = that.getValue();

            if (!that.trigger('beforeChange', val)) {
                setInputValue.call(that, that.lastValue, that.lastText);
                $input.focus().select();
                return;
            }
            that.lastValue = val;
            that.lastText = text;

            that.trigger('changed', val);
        });

        //add callback
        if (typeof(opts.onChange) === 'function') {
            this.on('changed', opts.onChange);
        }

        if (typeof(opts.onBeforeChange) === 'function') {
            this.on('beforeChange', opts.onBeforeChange);
        }
    }

    function setTextFieldName() {
        var opts = this.options;
        this.textName = opts.textName || (this.name + 'Text');

        delete opts.textName;
    }

    function getInitText() {
        var opts = this.options;
        this.initText = opts.mode === 1 ? opts.defaultText : opts.text;
        delete opts.defaultText;
        delete opts.text;
    }

    function setInputValue(value, text) {
        this.$input.val(text);
        this.value = value;
    }

    function setInitValue(value, text) {
        setInputValue.call(this, value, text);
        this.lastValue = value;
        this.lastText = text;
    }

    function init() {
        var $element = this.$element,
            $tree,
            initValue = this.initValue,
            $input,
            opts = this.options;

        this.parseOptions();
        setTextFieldName.apply(this);


        $input = this.$element.find('.combotree-input').prop('readonly', true);
        this.$input = $input;
        $tree = $('<div class="dropdown-tree"></div>');
        this.$tree = $tree;
        $element.append($tree);
        this.id = util.generateId($element, 'combotree-');
        this.filter = {};

        getInitText.apply(this);
        bindEvents.apply(this);

        setInitValue.call(this, initValue, this.initText);
        $input.prop('name', this.name);
        this.trigger('inited');
        opts.autoload && this.load();
    }

    function checkNodesByValue(value) {
        var $tree = this.$tree,
            treeview = $tree.data('treeview'),
            nodes = treeview.getNodes(),
            opts = this.options,
            method = opts.multiSelect ? 'checkNode' : 'selectNode',
            methodAll = opts.multiSelect ? 'uncheckAll' : 'unselectAll',
            vals = value.split(','),
            l = vals.length,
            temp,
            valueNodes = [];

        if (!treeview) return;

        while (l--) {
            temp = nodes.filter(function (e) {
                return e.value === vals[l];
            });
            temp.length && valueNodes.push(temp[0].nodeId);
        }

        l = valueNodes.length;
        if (!l) return;

        treeview[methodAll]({silent: true});

        while (l--) {
            treeview[method](valueNodes[l], {silent: l !== 0});
        }
    }

    function updateFilter(params, append) {
        var oldFilter = this.filter, filter;
        if (params) {
            if (append === false) {
                filter = params;
            } else {
                filter = $.extend({}, oldFilter, params);
            }
            this.filter = filter;
        }
    }

    function _updateTextValue(treeview, that) {
        var opts = that.options;
        var selectNodes;
        if (opts.multiSelect) {
            selectNodes = treeview.getChecked();
            if (!opts.multiSelectParent) {
                selectNodes = selectNodes.filter(function (elem) {
                    return elem.nodes ? false : true;
                });
            }
        } else {
            selectNodes = treeview.getSelected();
        }
        var l = selectNodes.length,
            arrValue = [],
            arrText = [];
        for (var i = 0; i < l; i++) {
            arrText.push(selectNodes[i].text);
            arrValue.push(selectNodes[i].value);
        }

        that.trigger('treeValueChanged', {
            text: arrText.join(','),
            value: arrValue.join(',')
        });
    }

    function parseDataFields(data, textField, valueField, dTextField, dValueField) {
        var l = data.length, t, nodes;

        for (var i = 0; i < l; i++) {
            t = data[i];
            if (textField !== dTextField) {
                t[dTextField] = t[textField];
            }
            if (valueField !== dValueField) {
                t[dValueField] = t[valueField];
            }
            nodes = t.nodes;

            if (nodes && typeof(nodes) == 'object' && nodes.constructor === Array) {
                parseDataFields(nodes, textField, valueField, dTextField, dValueField);
            }
        }
    }

    function parseTreeData(newData, data, idField, parentIdField, parentId) {
        var l,
            temp;

        temp = data.filter(function (e) {
            return e[parentIdField] == parentId;
        });

        l = temp.length;
        for (var i = 0; i < l; i++) {
            temp[i].nodes = [];
            parseTreeData(temp[i].nodes, data, idField, parentIdField, temp[i][idField]);
            newData.push(temp[i]);
        }
        return newData;
    }

    function resolveTreeData(data, pathNodes, params) {
        var node;
        var l = data.length;
        for (var i = 0; i < l; i++) {
            node = data[i];
            resolveNode(node, params);
            pathNodes.push(node);

            var state = node.state;
            var hasChilds = node.nodes && node.nodes.constructor == Array;

            if (hasChilds) {
                resolveTreeData(node.nodes, pathNodes, params);
            }

            if (state.selected || state.checked) {
                setPathNodesState(pathNodes, params.ifCheckParent);
            }

            pathNodes.pop();
        }
    }

    function resolveNode(node, params) {
        var multiSelect = params.multiSelect;
        var singleSelectParent = params.singleSelectParent;
        var vals = params.vals;
        var useDefaultBehavior = params.useDefaultBehavior;

        if (node.nodes && node.nodes.length == 0) {
            delete node.nodes;
        }

        var childNodes = node.nodes;
        var hasChildren = childNodes && childNodes.constructor == Array && childNodes.length > 0;

        node.state = node.state || {};

        if (multiSelect) {
            node.selectable = hasChildren ? false : true;

            if (!useDefaultBehavior) {
                node.state.checked = vals.filter(function (e) {
                    return e == node.value;
                }).length > 0 ? true : false;
            }
        } else {
            node.selectable = !singleSelectParent && hasChildren ? false : true;
            if (!useDefaultBehavior) {
                node.state.selected = node.value == vals[0] ? true : false;
            }
        }
        if (!node.selectable) {
            node.color = 'gray';
        } else {
            node.color = '';
        }
    }

    function setPathNodesState(pathNodes, ifCheckParent) {
        var l = pathNodes.length;
        for (var i = 0; i < l; i++) {
            var node = pathNodes[i];
            var state = node.state;
            if (node.nodes && node.nodes.length) {
                state.expanded = true;
                if (ifCheckParent) {
                    state.checked = true;
                }
            }
        }
    }

    function clearComboTree(e, filter) {
        var list;
        if (filter) {
            list = $('.combotree').filter(filter);
        } else {
            list = $('.combotree');
        }
        list.each(function () {
            $(this).removeClass('open');
        });
    }

    function Combotree(element, options) {
        formControl.apply(this, arguments);
        init.apply(this);
    }

    Combotree.getNamespace = function () {
        return 'util.formControl.combotree';
    };

    Combotree.DEFAULTS = $.extend({}, formControl.prototype.constructor.DEFAULTS, {
        url: '',
        levels: 2,
        textName: '',
        defaultParentId: 0,
        idField: 'id',
        textField: 'text',
        valueField: 'value',
        parentIdField: 'parentId',
        autoTreeData: true,
        multiSelect: false,//false为单选，true为多选
        multiSelectType: {//多选有效，Y表示选中时是否影响父子节点，N表示取消选中时是否影响父子节点，ps表示同时影响，p表示只影响父级，s表示只影响子级
            Y: 'ps',
            N: 'ps'
        },
        multiSelectParent: true,//多选有效，表示getValue时是否返回非叶子节点的value
        singleSelectParent: true,//单选有效，表示是否允许选择非叶子节点
        format: $.noop,
        showExpandIcon: true,
        useDefaultBehavior: false,
        ajaxMethod: 'get',
        color: "#428bca",
        expandIcon: 'glyphicon glyphicon-folder-close',
        collapseIcon: 'glyphicon glyphicon-folder-open',
        defaultText: '',
        autoload: true,
        text: ''
    });

    Combotree.prototype = $.extend({}, formControl.prototype);
    Combotree.prototype.constructor = Combotree;

    Combotree.prototype.getDefaults = function () {
        return Combotree.DEFAULTS;
    };

    Combotree.prototype.isMultiControl = function () {
        return true;
    };

    Combotree.prototype.getExtraValue = function () {
        var obj = {};
        obj[this.getTextName()] = this.getText();
        return obj;
    };

    Combotree.prototype.getTextName = function () {
        return this.textName;
    };

    Combotree.prototype.parseOptions = function () {
        var opts = this.options;
        if (typeof(opts.multiSelectType) === 'string') {
            opts.multiSelectType = JSON.parse(opts.multiSelectType.replace(/'/g, '"'));
        }
    };

    Combotree.prototype.getTreeOptions = function () {
        var o = {};
        var opts = this.options;
        if (opts.expandIcon) {
            o.expandIcon = opts.expandIcon;
        }
        if (opts.collapseIcon) {
            o.collapseIcon = opts.collapseIcon;
        }
        return $.extend({
            showBorder: false,
            color: opts.color,
            levels: opts.levels,
            showCheckbox: opts.multiSelect,
            multiSelect: false
        }, o);
    };

    Combotree.prototype.toggle = function (e) {
        clearComboTree(e, ':not(#' + this.id + ')');//隐藏其它tree
        var $element = this.$element;
        $element.toggleClass('open');
    };

    Combotree.prototype.destroy = function () {
        var treeview = this.$tree.data('treeview');
        if (treeview) {
            treeview.remove();
            this.$tree.html('');
        }
    };

    Combotree.prototype.load = function (url, params, append) {
        var that = this,
            opts = this.options,
            $tree;

        url = url || opts.url;
        if (this.loading || !url) return;

        this.loading = true;
        $tree = that.$tree;
        this.destroy();
        //更新过滤条件
        updateFilter.call(this, params, append);

        util.ajax()[opts.ajaxMethod](url, params).success(function (res) {
            if (res.code == 200) {
                _loadData(res.data);
            } else {
                util.log(res.message);
            }
        });

        function _loadData(data) {
            that.loading = false;

            if (typeof(data) != 'object' || data.constructor != Array || data.length === 0) {
                return;
            }

            if (!opts.autoTreeData) {
                data = parseTreeData([], data, opts.idField, opts.parentIdField, opts.defaultParentId);
            }

            if (opts.textField !== Combotree.DEFAULTS.textField || opts.valueField !== Combotree.DEFAULTS.valueField) {
                parseDataFields(data, opts.textField, opts.valueField, Combotree.DEFAULTS.textField,Combotree.DEFAULTS.valueField);
            }

            var value = that.getValue(),
                initVals,
                treeOpts,
                multiSelectType,
                showCheckbox,
                ifChecked,
                params;

            initVals = (value + '').split(',');
            treeOpts = that.getTreeOptions();

            multiSelectType = opts.multiSelectType;
            showCheckbox = treeOpts.showCheckbox;
            ifChecked = showCheckbox && (multiSelectType.Y == 'p' || multiSelectType.Y == 'ps');
            params = {
                multiSelect: showCheckbox,
                singleSelectParent: opts.singleSelectParent,
                vals: initVals,
                useDefaultBehavior: opts.useDefaultBehavior,
                ifCheckParent: ifChecked
            };
            resolveTreeData(data, [], params);

            $tree.treeview($.extend(treeOpts, {data: data}));

            _updateTextValue($tree.data('treeview'), that);

            if (treeOpts.showCheckbox) {
                _bindMultiSelectHandler();
            } else {
                _bindSingleSelectHandler();
            }
        }

        function _bindMultiSelectHandler() {
            var type = opts.multiSelectType,
                treeview = $tree.data('treeview');

            $tree.on('nodeSelected', function (e, node) {
                treeview.checkNode(node.nodeId);
            });

            $tree.on('nodeUnselected', function (e, node) {
                e.preventDefault();
                treeview.uncheckNode(node.nodeId);
            });

            $tree.on('nodeChecked ', function (e, node) {
                if (type.Y == 'p' || type.Y == 'ps') {
                    var currentNode = node;
                    var parentNode = treeview.getParent(currentNode.nodeId);
                    while (parentNode) {
                        var state = parentNode.state;
                        if (!state.disabled && !state.checked) {
                            treeview.checkNode(parentNode.nodeId, {silent: true});
                        }

                        currentNode = parentNode;
                        parentNode = treeview.getParent(currentNode.nodeId);
                    }
                }

                if (type.Y == 's' || type.Y == 'ps') {
                    _checkAllChildNode(node.nodes);
                }

                _updateTextValue(treeview, that);

                function _checkAllChildNode(childNodes) {
                    var node;
                    if (childNodes && childNodes.length > 0) {
                        for (var i = childNodes.length - 1; i >= 0; i--) {
                            node = childNodes[i];
                            var state = node.state;
                            if (!state.disabled && !state.checked) {
                                treeview.checkNode(node.nodeId, {silent: true});
                                _checkAllChildNode(node.nodes);
                            }
                        }
                    }
                }
            });

            $tree.on('nodeUnchecked ', function (e, node) {
                if (type.N == 'p' || type.N == 'ps') {
                    var currentNode = node;
                    var parentNode = treeview.getParent(currentNode.nodeId);
                    while (parentNode) {
                        var childNodes = parentNode.nodes;
                        var flag = true;
                        for (var i = childNodes.length - 1; i >= 0; i--) {
                            var temp = childNodes[i];
                            if (temp != currentNode && temp.state.checked) {
                                flag = false;
                            }
                        }
                        if (!flag) {
                            break;
                        } else {
                            var state = parentNode.state;
                            if (!state.disabled && state.checked) {
                                treeview.uncheckNode(parentNode.nodeId, {silent: true});
                            }

                            currentNode = parentNode;
                            parentNode = treeview.getParent(currentNode.nodeId);
                        }
                    }
                }
                if (type.N == 's' || type.N == 'ps') {
                    _uncheckAllChildNode(node.nodes);
                }

                _updateTextValue(treeview, that);

                function _uncheckAllChildNode(childNodes) {
                    var node;
                    if (childNodes && childNodes.length > 0) {
                        for (var i = childNodes.length - 1; i >= 0; i--) {
                            node = childNodes[i];
                            var state = node.state;
                            if (!state.disabled && state.checked) {
                                treeview.uncheckNode(node.nodeId, {silent: true});
                            }
                            _uncheckAllChildNode(node.nodes);
                        }
                    }
                }
            });
        }

        function _bindSingleSelectHandler() {
            var treeview = $tree.data('treeview');
            $tree.on('nodeSelected', function (e, node) {
                _updateTextValue(treeview, that);
                that.hide();
            });

            $tree.on('nodeUnselected', function (e, node) {
                e.preventDefault();
                _updateTextValue(treeview, that);
            });
        }
    };

    Combotree.prototype.hide = function (e) {
        this.$element.removeClass('open');
    };

    Combotree.prototype.setValue = function (value) {
        var value = util.isEmpty(value) ? '' : value;

        if (value === this.getValue()) return;
        checkNodesByValue.call(this, value);
    };

    Combotree.prototype.getValue = function () {
        return this.value;
    };

    Combotree.prototype.getText = function () {
        return this.$input.val();
    };

    Combotree.prototype.disable = function () {
        this.$input.addClass('disabled').prop('disabled', true);
        offClickEvent.apply(this);
    };

    Combotree.prototype.enable = function () {
        this.$input.removeClass('disabled').prop('disabled', false);
        subscribeClickEvent.apply(this);
    };

    Combotree.prototype.getField = function (field) {
        if (!field) return;
        var treeview = this.$tree.data('treeview'),
            opts = this.options,
            selectNodes,
            i,
            l,
            arrValue;
        if (opts.multiSelect) {
            selectNodes = treeview.getChecked();
            if (!opts.multiSelectParent) {
                selectNodes = selectNodes.filter(function (elem) {
                    return elem.nodes ? false : true;
                });
            }
        } else {
            selectNodes = treeview.getSelected();
        }

        l = selectNodes.length;
        arrValue = [];
        for (i = 0; i < l; i++) {
            arrValue.push(selectNodes[i][field]);
        }

        return arrValue.join(',');
    };

    Combotree.prototype.reset = function () {
        setInputValue.call(this, this.initValue);
    };

    _combotree = function (selector, option) {
        var obj = [],
            ns = Combotree.getNamespace();

        $(selector).each(function () {
            var $this = $(this),

                data = $this.data(ns),
                options = typeof option == 'object' && option;

            if (!data) $this.data(ns, (data = new Combotree(this, options)));

            obj.push(data);
        });

        if (obj.length == 1) return obj[0];
        return obj;
    };

    $.extend(formControl, {
        combotree: _combotree
    });

}(window, jQuery);
/**
 * author zengxf
 * added 2015-09-09
 */
+function (_, $) {


    var util = _.util || {},
        formControl = util.formControl = util.formControl || {},
        _date;

    function offClickEvent() {
        var $element = this.$element;
        $element[0].onclick = null;
    }

    function subscribeClickEvent() {
        var $element = this.$element,
            config = $element.data('config') || 'yyyy-MM-dd',
            config = util.config.datetimepicker[config];

        $element[0].onclick = function () {
            var $input = $element.find('input.form_date_input');
            WdatePicker({
                el: $input[0],
                dateFmt: config,
                onpicked: function () {
                    $input.trigger('change');
                }
            });
        }
    }

    function bindEvents() {
        var opts = this.options,
            that = this,
            $element = this.$element,
            $input = this.$input;

        subscribeClickEvent.apply(this);

        $element.on('change.' + Date.getNamespace(), '.form_date_input', function (e) {
            var val = that.getValue();

            if (!that.trigger('beforeChange', val)) {
                setInputValue.call(that, that.lastValue);
                $input.focus().select();
                return;
            }
            that.lastValue = val;
            that.trigger('changed', val);
        });

        if (typeof(opts.onChange) === 'function') {
            this.on('changed', opts.onChange);
        }

        if (typeof(opts.onBeforeChange) === 'function') {
            this.on('beforeChange', opts.onBeforeChange);
        }
    }

    function setInputValue(value) {
        this.$input.val(value);
    }

    function setInitValue(value) {
        setInputValue.call(this, value);
        this.lastValue = value;
    }

    function init() {
        var $input;

        $input = this.$element.find('.form_date_input').prop('readonly', true);
        this.$input = $input;

        bindEvents.apply(this);

        setInitValue.call(this, this.initValue);
        $input.prop('name', this.name);
        this.trigger('inited');
    }

    function Date(element, options) {
        formControl.apply(this, arguments);
        init.apply(this);
    }

    Date.getNamespace = function () {
        return 'util.formControl.date';
    };

    Date.DEFAULTS = $.extend({}, formControl.prototype.constructor.DEFAULTS, {
        time: false,
        format: ''
    });

    Date.prototype = $.extend({}, formControl.prototype);
    Date.prototype.constructor = Date;

    Date.prototype.getDefaults = function () {
        return Date.DEFAULTS;
    };

    Date.prototype.setValue = function (value) {
        var $input = this.$input,
            value = util.isEmpty(value) ? '' : value;

        if (value === this.getValue()) return;

        setInputValue.call(this, value);
        $input.trigger('change');
    };

    Date.prototype.getValue = function () {
        return this.$input.val();
    };

    Date.prototype.disable = function () {
        this.$input.addClass('disabled').prop('disabled', true);
        offClickEvent.apply(this);
    };

    Date.prototype.enable = function () {
        this.$input.removeClass('disabled').prop('disabled', false);
        subscribeClickEvent.apply(this);
    };

    Date.prototype.reset = function () {
        setInputValue.call(this, this.initValue);
    };

    _date = function (selector, option) {
        var obj = [],
            ns = Date.getNamespace();

        $(selector).each(function () {
            var $this = $(this),

                data = $this.data(ns),
                options = typeof option == 'object' && option;

            if (!data) $this.data(ns, (data = new Date(this, options)));

            obj.push(data);
        });

        if (obj.length == 1) return obj[0];
        return obj;
    };

    $.extend(formControl, {
        date: _date
    });

}(window, jQuery);
/**
 * author zengxf
 * added 2015-09-09
 */
+function (_, $) {


    var util = _.util || {},
        formControl = util.formControl = util.formControl || {},
        _lookup;

    function offClickEvent() {
        var $element = this.$element;
        $element.off('click.' + Lookup.getNamespace());
    }

    function subscribeClickEvent() {
        var $element = this.$element,
            opts = this.options;

        if (typeof(opts.onClick) === 'function') {
            $element.on('click.' + Lookup.getNamespace(), $.proxy(opts.onClick, this));
        }
    }

    function bindEvents() {
        var opts = this.options,
            that = this,
            $element = this.$element,
            $input = this.$input,
            ns = Lookup.getNamespace();

        subscribeClickEvent.apply(this);

        $element.on('change.' + ns, '.lookup-input', function (e) {
            var text = that.getText(),
                val = that.getValue();

            if (!that.trigger('beforeChange', val)) {
                setInputValue.call(that, that.lastValue, that.lastText);
                $input.focus().select();
                return;
            }
            that.lastValue = val;
            that.lastText = text;

            that.trigger('changed', val);
        });

        //add callback
        if (typeof(opts.onChange) === 'function') {
            this.on('changed', opts.onChange);
        }

        if (typeof(opts.onBeforeChange) === 'function') {
            this.on('beforeChange', opts.onBeforeChange);
        }
    }

    function setTextFieldName(){
        var opts = this.options;
        this.textName = opts.textName || (this.name + 'Text');

        delete opts.textName;
    }

    function getInitText() {
        var opts = this.options;
        this.initText = opts.mode === 1 ? opts.defaultText : opts.text;
        delete opts.defaultText;
        delete opts.text;
    }

    function setInputValue(value, text) {
        this.$input.val(text);
        this.value = value;
    }

    function setInitValue(value, text) {
        setInputValue.call(this, value, text);
        this.lastValue = value;
        this.lastText = text;
    }

    function init() {
        var $input;

        $input = this.$element.find('.lookup-input').prop('readonly', true);
        this.$input = $input;

        setTextFieldName.apply(this);
        getInitText.apply(this);
        bindEvents.apply(this);

        setInitValue.call(this, this.initValue, this.initText);
        $input.prop('name', this.name);
        this.trigger('inited');
    }

    function Lookup(element, options) {
        formControl.apply(this, arguments);
        init.apply(this);
    }

    Lookup.getNamespace = function () {
        return 'util.formControl.lookup';
    };

    Lookup.DEFAULTS = $.extend({}, formControl.prototype.constructor.DEFAULTS, {
        defaultText: '',
        text: '',
        textName: '',
        onClick: $.noop
    });

    Lookup.prototype = $.extend({}, formControl.prototype);
    Lookup.prototype.constructor = Lookup;

    Lookup.prototype.getDefaults = function () {
        return Lookup.DEFAULTS;
    };

    Lookup.prototype.isMultiControl = function () {
        return true;
    };

    Lookup.prototype.getTextName = function () {
        return this.textName;
    };

    Lookup.prototype.getExtraValue = function(){
        var obj = {};
        obj[this.getTextName()] = this.getText();
        return obj;
    };

    Lookup.prototype.setValue = function (value, text) {
        var $input = this.$input,
            value = util.isEmpty(value) ? '' : value,
            text = util.isEmpty(text) ? '' : text;

        if (value === this.getValue()) return;

        setInputValue.call(this, value, text);
        $input.trigger('change');
    };

    Lookup.prototype.getValue = function () {
        return this.value;
    };

    Lookup.prototype.getText = function () {
        return this.$input.val();
    };

    Lookup.prototype.disable = function () {
        this.$input.addClass('disabled').prop('disabled', true);
        offClickEvent.apply(this);
    };

    Lookup.prototype.enable = function () {
        this.$input.removeClass('disabled').prop('disabled', false);
        subscribeClickEvent.apply(this);
    };

    Lookup.prototype.reset = function () {
        setInputValue.call(this, this.initValue);
    };

    _lookup = function (selector, option) {
        var obj = [],
            ns = Lookup.getNamespace();

        $(selector).each(function () {
            var $this = $(this),

                data = $this.data(ns),
                options = typeof option == 'object' && option;

            if (!data) $this.data(ns, (data = new Lookup(this, options)));

            obj.push(data);
        });

        if (obj.length == 1) return obj[0];
        return obj;
    };

    $.extend(formControl, {
        lookup: _lookup
    });

}(window, jQuery);
/**
 * author zengxf
 * added 2015-09-09
 */
+function (_, $) {


    var util = _.util || {},
        formControl = util.formControl = util.formControl || {},
        _number;

    function bindEvents() {
        var opts = this.options,
            decimalDigits = opts.decimalDigits,
            min = opts.fvBetweenMin,
            max = opts.fvBetweenMax,
            that = this,
            $element = this.$element,
            preValue;

        $element.on('change.' + NumberText.getNamespace(), function (e) {
            var val = that.getValue(),
                isEmpty = util.isEmpty(val),
                isNumberText = $.isNumeric(val);

            isNumberText && (val = parseValue(val, decimalDigits, max, min));

            if ((!isEmpty && !isNumberText) || !that.trigger('beforeChange', val)) {
                setInputValue.call(that, that.lastValue);
                $element.focus().select();
                return;
            }

            that.lastValue = val;
            $element.val(val);
            that.trigger('changed', val);
        });


        $element.on('focus.' + NumberText.getNamespace(), function (e) {
            preValue = this.value;
        });

        $element.on('blur.' + NumberText.getNamespace(), function (e) {
            if(preValue != this.value) {
                $(this).trigger('change');
            }
        });

        $element.on('keyup.' + NumberText.getNamespace() + ' afterpaste.' + NumberText.getNamespace(), function (e) {
            if (e.which == 36 || e.which == 35 || e.which == 37 || e.which == 39) return;

            var value = this.value.replace(/[^-\.\d]/g, '');
            try {
                this.innerText = value;
            } catch (e) {
                this.value = value;
            }
        });

        if (typeof(opts.onChange) === 'function') {
            this.on('changed', opts.onChange);
        }

        if (typeof(opts.onBeforeChange) === 'function') {
            this.on('beforeChange', opts.onBeforeChange);
        }
    }

    function parseValue(value, decimalDigits, max, min) {
        value = parseFloat(value);
        max = $.isNumeric(max) && Number(max);
        min = $.isNumeric(min) && Number(min);
        decimalDigits = decimalDigits && decimalDigits > 0 ? decimalDigits : 0;

        if (max !== false && value > max) {
            return max.toFixed(decimalDigits);
        } else if (min !== false && value < min) {
            return min.toFixed(decimalDigits);
        } else {
            return value.toFixed(decimalDigits)
        }
    }

    function validateInitValue() {
        if (!$.isNumeric(this.initValue)) {
            this.initValue = '';
            return;
        }

        var decimalDigits = this.options.decimalDigits;
        decimalDigits = decimalDigits && decimalDigits > 0 ? decimalDigits : 0;
        this.initValue = Number(this.initValue).toFixed(decimalDigits);
    }

    function setInputValue(value) {
        this.$element.val(value);
    }

    function setInitValue(value) {
        setInputValue.call(this, value);
        this.lastValue = value;
    }

    function init() {
        bindEvents.apply(this);
        validateInitValue.apply(this);
        setInitValue.call(this, this.initValue);
        this.$element.prop('name', this.name);
        this.trigger('inited');
    }

    function NumberText(element, options) {
        formControl.apply(this, arguments);
        init.apply(this);
    }

    NumberText.getNamespace = function () {
        return 'util.formControl.number';
    };

    NumberText.DEFAULTS = $.extend(NumberText.DEFAULTS, formControl.prototype.constructor.DEFAULTS, {
        decimalDigits: -1,
        fvBetweenMin: null,
        fvBetweenMax: null
    });

    delete NumberText.DEFAULTS.value;

    NumberText.prototype = $.extend({}, formControl.prototype);
    NumberText.prototype.constructor = NumberText;

    NumberText.prototype.getDefaults = function () {
        return NumberText.DEFAULTS;
    };

    NumberText.prototype.setValue = function (value) {
        var $element = this.$element,
            value = util.isEmpty(value) ? '' : value;

        if (value === this.getValue() || !$.isNumeric(value)) return;
        setInputValue.call(this, value);
        $element.trigger('change');
    };

    NumberText.prototype.getValue = function () {
        return this.$element.val();
    };

    NumberText.prototype.disable = function () {
        this.$element.addClass('disabled').prop('disabled', true);
    };

    NumberText.prototype.enable = function () {
        this.$element.removeClass('disabled').prop('disabled', false);
    };

    NumberText.prototype.reset = function () {
        setInputValue.call(this, this.initValue);
    };

    _number = function (selector, option) {
        var obj = [],
            ns = NumberText.getNamespace();

        $(selector).each(function () {
            var $this = $(this),

                data = $this.data(ns),
                options = typeof option == 'object' && option;

            if (!data) $this.data(ns, (data = new NumberText(this, options)));

            obj.push(data);
        });

        if (obj.length == 1) return obj[0];
        return obj;
    };

    $.extend(formControl, {
        number: _number
    });

}(window, jQuery);
/**
 * author zengxf
 * added 2015-09-09
 */
+function (_, $) {


    var util = _.util || {},
        formControl = util.formControl = util.formControl || {},
        _radio;

    function bindEvents() {
        var opts = this.options,
            that = this,
            $element = this.$element;

        $element.on('change.' + Radio.getNamespace(), getInputSelector.apply(this), function (e) {
            var val = that.getValue();

            if (!that.trigger('beforeChange', val)) {
                setInputValue.call(that, that.lastValue);
                return;
            }
            that.lastValue = val;
            that.trigger('changed', val);
        });

        if (typeof(opts.onChange) === 'function') {
            this.on('changed', opts.onChange);
        }

        if (typeof(opts.onBeforeChange) === 'function') {
            this.on('beforeChange', opts.onBeforeChange);
        }
    }

    function setInputValue(value) {
        if(value !== '') {
            this.$inputs.filter('[value="' + value + '"]').prop('checked', true);
        } else {
            this.$inputs.each(function(){
                $(this).prop('checked', false);
            })
        }
    }

    function setInitValue(value) {
        setInputValue.call(this, value);
        this.lastValue = value;
    }

    function init() {
        var $element = this.$element,
            $inputs;

        bindEvents.apply(this);


        $inputs = $element.find(getInputSelector.apply(this));
        this.$inputs = $inputs;
        $inputs.prop('name', this.name);
        setInitValue.call(this, this.initValue);
        this.trigger('inited');
    }

    function getInputSelector() {
        return 'input[type=radio]';
    }

    function Radio(element, options) {
        formControl.apply(this, arguments);
        init.apply(this);
    }

    Radio.getNamespace = function () {
        return 'util.formControl.radio';
    };

    Radio.DEFAULTS = $.extend({}, formControl.prototype.constructor.DEFAULTS, {});

    Radio.prototype = $.extend({}, formControl.prototype);
    Radio.prototype.constructor = Radio;

    Radio.prototype.getDefaults = function () {
        return Radio.DEFAULTS;
    };

    Radio.prototype.setValue = function (value) {
        var $inputs = this.$inputs;

        value = util.isEmpty(value) ? '' : (value + '');
        if (value === this.getValue()) return;

        setInputValue.call(this, value);
        $inputs.eq(0).trigger('change');
    };

    Radio.prototype.getValue = function () {
        return this.$inputs.filter(':checked').val();
    };

    Radio.prototype.disable = function () {
        var $element = this.$element;
        $element.addClass('disabled');
        this.$inputs.prop('disabled', true);
    };

    Radio.prototype.enable = function () {
        var $element = this.$element;
        $element.removeClass('disabled');
        this.$inputs.prop('disabled', false);
    };

    Radio.prototype.reset = function () {
        setInputValue.call(this, this.initValue);
    };

    _radio = function (selector, option) {
        var obj = [],
            ns = Radio.getNamespace();

        $(selector).each(function () {
            var $this = $(this),

                data = $this.data(ns),
                options = typeof option == 'object' && option;

            if (!data) $this.data(ns, (data = new Radio(this, options)));

            obj.push(data);
        });

        if (obj.length == 1) return obj[0];
        return obj;
    };

    $.extend(formControl, {
        radio: _radio
    });

}(window, jQuery);
/**
 * author zengxf
 * added 2015-09-09
 */
+function (_, $) {


    var util = _.util || {},
        formControl = util.formControl = util.formControl || {},
        _select;

    function bindEvents() {
        var opts = this.options,
            that = this,
            $element = this.$element;

        $element.on('change.' + Select.getNamespace(), function (e) {
            var val = that.getValue();
            if (!that.trigger('beforeChange', val)) {
                setInputValue.call(that, that.lastValue);
                return;
            }
            that.lastValue = val;
            that.trigger('changed', val);
        });

        if (typeof(opts.onChange) === 'function') {
            this.on('changed', opts.onChange);
        }

        if (typeof(opts.onBeforeChange) === 'function') {
            this.on('beforeChange', opts.onBeforeChange);
        }

        if (typeof(opts.onReady) === 'function') {
            this.on('ready', opts.onReady);
        }
    }

    function setInputValue(value) {
        this.$element.val(value.split(','));
    }

    function setInitValue(value) {
        setInputValue.call(this, value);
        this.lastValue = value;
    }

    function init() {
        var opts = this.options;
        bindEvents.apply(this);
        if (!opts.url) {
            afterHtmlReady.apply(this);
        } else if(opts.autoload) {
            this.filter = {};
            this.load();
        }
        this.$element.prop('name', this.name);
        this.trigger('inited');
    }

    function updateFilter(params, append) {
        var oldFilter = this.filter, filter;
        if (params) {
            if (append === false) {
                filter = params;
            } else {
                filter = $.extend({}, oldFilter, params);
            }
            this.filter = filter;
        }
    }

    function resolveResponse(that, data) {
        var opts = that.options,
            textField = opts.textField,
            valueField = opts.valueField,
            o,
            i,
            l = data && data.length,
            $element = that.$element,
            $o,
            field;

        if(!data) data = [];

        if (opts.autoAddEmptyOption) {
            o = {};
            o[textField] = opts.emptyOptionText;
            o[valueField] = '';
            l = data.unshift(o);
        }

        for (i = 0; i < l; i++) {
            o = data[i];
            $o = $(['<option value="',
                o[valueField],
                '">',
                o[textField],
                '</option>'].join(''));

            delete o[valueField];
            for (field in o) {
                $o.data(field + 'Field', o[field]);
            }

            $element.append($o);
        }

        afterHtmlReady.apply(that);
    }

    function ifSelect2(that) {
        return that.$element.hasClass('use-select2');
    }

    function getSelect2(that) {
        if (ifSelect2(that)) {
            return that.$element.data('select2');
        }
        return null;
    }

    function setUpSelect2() {
        var opts = this.options,
            config = opts.config;

        config = util.config.select2[config];
        if(opts.enableSearch) {
            delete config['minimumResultsForSearch'];
        }
        this.$element.select2(config);
    }

    function afterHtmlReady() {
        var initValue = util.isEmpty(this.initValue) ? '' : this.initValue;

        if (!initValue && this.options.selectFirstOption) {
            initValue = getFirstNonEmptyOption.apply(this);
        }

        setInitValue.call(this,initValue + '');
        if (ifSelect2(this)) {
            setUpSelect2.apply(this);
        }

        this.trigger('ready', this);
    }

    function getFirstNonEmptyOption() {
        var $element = this.$element,
            $options = $element.find('option'),
            value = '',
            i,
            l = $options.length,
            $o;

        for (i = 0; i < l; i++) {
            $o = $options.eq(i);
            value = $o.attr('value');
            value = util.isEmpty(value) ? '' : value;
            if (value === '') {
                value = $o.prop('value');
                value = util.isEmpty(value) ? '' : value;
            }
            if (value !== '') {
                return value;
            }
        }
        return value;
    }

    function Select(element, options) {
        formControl.apply(this, arguments);
        init.apply(this);
    }

    Select.getNamespace = function () {
        return 'util.formControl.select';
    };

    Select.DEFAULTS = $.extend(Select.DEFAULTS, formControl.prototype.constructor.DEFAULTS, {
        textField: 'text',
        valueField: 'value',
        config: 'simple',
        url: '',
        ajaxMethod: 'get',
        selectFirstOption: false,
        autoAddEmptyOption: true,
        emptyOptionText: '&nbsp;',
        enableSearch: false,
        autoload: true,
        onReady: $.noop
    });

    Select.prototype = $.extend({}, formControl.prototype);
    Select.prototype.constructor = Select;

    Select.prototype.getDefaults = function () {
        return Select.DEFAULTS;
    };

    Select.prototype.destroy = function () {
        var $element = this.$element,
            select2 = getSelect2(this);
        if (select2 != null) {
            select2.destroy();
        }

        $element.html('');
    };

    Select.prototype.refresh = function () {
        this.load();
    };

    Select.prototype.getFirstNonEmptyValue = getFirstNonEmptyOption;

    Select.prototype.load = function (url, params, append) {
        var that = this,
            opts = this.options;

        url = url || opts.url;
        if (this.loading || !url) return;

        this.loading = true;
        //更新过滤条件
        updateFilter.call(this, params, append);
        this.destroy();

        util.ajax()[opts.ajaxMethod](url, this.filter).success(function (res) {
            that.loading = false;
            var data = [];
            if (res.code == 200) {
                data = res.data;
            } else {
                util.log(res.message);
            }
            resolveResponse(that, data);
        });
    };

    Select.prototype.setValue = function (value) {
        var $element = this.$element;

        value = util.isEmpty(value) ? '' : (value + '');
        if(value === this.getValue()) return;

        setInputValue.call(this, value);
        $element.trigger('change');
    };

    Select.prototype.getValue = function () {
        var value = this.$element.val();
        if (value && typeof(value) == 'object' && value.constructor === Array) {
            return value.join(',');
        }
        return util.isEmpty(value) ? '' : value;
    };

    Select.prototype.getText = function () {
        var $element = this.$element,
            $options = $element.find('option'),
            vals = [],
            value = '',
            i,
            l = $options.length,
            $o;

        for (i = 0; i < l; i++) {
            $o = $options.eq(i);
            if ($o[0].selected) {
                value = $o.text();
                value = util.isEmpty(value) ? '' : value;
                if (value !== '') {
                    vals.push(value);
                }
            }
        }
        return vals.join(',');
    };

    Select.prototype.getField = function (field) {
        var $element = this.$element,
            $options = $element.find('option'),
            vals = [],
            value = '',
            i,
            l = $options.length,
            $o;

        for (i = 0; i < l; i++) {
            $o = $options.eq(i);
            if ($o[0].selected) {
                value = $o.data(field + 'Field');
                value = util.isEmpty(value) ? '' : value;
                if (value !== '') {
                    vals.push(value);
                }
            }
        }
        return vals.join(',');
    };

    Select.prototype.disable = function () {
        this.$element.addClass('disabled').prop('disabled', true);
    };

    Select.prototype.enable = function () {
        this.$element.removeClass('disabled').prop('disabled', false);
    };

    Select.prototype.reset = function () {
        this.setValue(this.initValue);
    };

    _select = function (selector, option) {
        var obj = [],
            ns = Select.getNamespace();

        $(selector).each(function () {
            var $this = $(this),

                data = $this.data(ns),
                options = typeof option == 'object' && option;

            if (!data) $this.data(ns, (data = new Select(this, options)));

            obj.push(data);
        });

        if (obj.length == 1) return obj[0];
        return obj;
    };

    $.extend(formControl, {
        select: _select
    });

}(window, jQuery);
/**
 * author zengxf
 * added 2015-09-09
 */
+function (_, $) {


    var util = _.util || {},
        formControl = util.formControl = util.formControl || {},
        _text;

    function bindEvents() {
        var opts = this.options,
            that = this,
            $element = this.$element;

        $element.on('change.' + Text.getNamespace(), function (e) {
            var val = that.getValue();

            if (!that.trigger('beforeChange', val)) {
                setInputValue.call(that, that.lastValue);
                $element.focus().select();
                return;
            }
            that.lastValue = val;
            that.trigger('changed', val);
        });

        if (typeof(opts.onChange) === 'function') {
            this.on('changed', opts.onChange);
        }

        if (typeof(opts.onBeforeChange) === 'function') {
            this.on('beforeChange', opts.onBeforeChange);
        }
    }

    function setInputValue(value) {
        this.$element.val(value);

        var e = this.$element[0];
        if (e.tagName === 'TEXTAREA') {
            var v = ' ' + value;
            e.value = v;
            e.value = v.substring(1)
        }
    }

    function setInitValue(value) {
        setInputValue.call(this, value);
        this.lastValue = value;
    }

    function init() {
        bindEvents.apply(this);
        setInitValue.call(this, this.initValue);
        this.$element.prop('name', this.name);
        this.trigger('inited');
    }

    function Text(element, options) {
        formControl.apply(this, arguments);
        init.apply(this);
    }

    Text.getNamespace = function () {
        return 'util.formControl.text';
    };

    Text.DEFAULTS = $.extend(Text.DEFAULTS, formControl.prototype.constructor.DEFAULTS, {});

    delete Text.DEFAULTS.value;

    Text.prototype = $.extend({}, formControl.prototype);
    Text.prototype.constructor = Text;

    Text.prototype.getDefaults = function () {
        return Text.DEFAULTS;
    };

    Text.prototype.setValue = function (value) {
        var $element = this.$element,
            value = util.isEmpty(value) ? '' : value;

        if (value === this.getValue()) return;
        setInputValue.call(this, value);
        $element.trigger('change');
    };

    Text.prototype.getValue = function () {
        return this.$element.val();
    };

    Text.prototype.disable = function () {
        this.$element.addClass('disabled').prop('disabled', true);
    };

    Text.prototype.enable = function () {
        this.$element.removeClass('disabled').prop('disabled', false);
    };

    Text.prototype.reset = function () {
        setInputValue.call(this, this.initValue);
    };

    _text = function (selector, option) {
        var obj = [],
            ns = Text.getNamespace();

        $(selector).each(function () {
            var $this = $(this),

                data = $this.data(ns),
                options = typeof option == 'object' && option;

            if (!data) $this.data(ns, (data = new Text(this, options)));

            obj.push(data);
        });

        if (obj.length == 1) return obj[0];
        return obj;
    };

    $.extend(formControl, {
        text: _text
    });

}(window, jQuery);
/**
 * author zengxf
 * added 2015-09-09
 */
+function (_, $) {


    var util = _.util || {},
        formControl = util.formControl = util.formControl || {},
        _ueditor;

    function bindEvents() {
        var opts = this.options,
            that = this,
            $element = this.$element;

        $element.on('change.' + Ueditor.getNamespace(), function (e) {
            var val = that.getValue();

            if (!that.trigger('beforeChange', val)) {
                setInputValue.call(that, that.lastValue);
                return;
            }
            that.lastValue = val;
            that.trigger('changed', val);
        });

        if (typeof(opts.onChange) === 'function') {
            this.on('changed', opts.onChange);
        }

        if (typeof(opts.onBeforeChange) === 'function') {
            this.on('beforeChange', opts.onBeforeChange);
        }
    }

    function offUeContentChange() {
        var editor = this.ue;
        editor.removeListener('contentChange', this.ueContentChange);
        this.ueContentChange = undefined;
    }

    function subscribeUeContentChange() {
        var editor = this.ue,
            $element = this.$element;

        this.ueContentChange = function () {
            $element.val(editor.getContent()).trigger('change');
        };

        editor.addListener('contentChange', this.ueContentChange);
    }

    function afterUeInited() {
        var editor = this.ue,
            that = this;

        editor && editor.ready(function () {
            that.ueReady = true;
            if (that.mode === 3) {
                editor.setDisabled();
            }

            //粘贴时只粘贴文本
            editor.execCommand('pasteplain');

            //粘贴后再次做格式清理
            editor.addListener('afterpaste', function (t, arg) {
                editor.execCommand('autotypeset');
            });

            //编辑器文本变化
            subscribeUeContentChange.apply(that);
        });
    }

    function setUploadFieldName() {
        var opts = this.options;
        this.uploadName = opts.uploadName || (this.name + 'UploadValue');

        delete opts.uploadName;
    }

    function setInputValue(value) {
        var ue = this.ue;

        this.$element.val(value);
        if (ue && this.ueReady) {
            offUeContentChange.apply(this);
            ue.setContent(value);
            subscribeUeContentChange.apply(this);
        }
    }

    function setInitValue(value) {
        setInputValue.call(this, value);
        this.lastValue = value;
    }

    function setUpUeditor() {
        var opts = this.options,
            height = opts.height,
            name = this.name,
            editorId = name + '-editor',
            editorName = name + '-text',
            initHtml = [
                '<script id="'
                , editorId
                , '" name="'
                , editorName
                , '" type="text/plain" style="width:100%;height:'
                , height
                , 'px;">'
                , this.initValue
                , '</script>'
            ].join('');

        this.$element.before(initHtml);

        this.ue = UE.getEditor(editorId, opts.ueConfig);

        afterUeInited.apply(this);
    }

    function init() {
        bindEvents.apply(this);
        setUploadFieldName.apply(this);
        setInitValue.call(this, this.initValue);
        this.$element.prop('name', this.name);
        setUpUeditor.apply(this);
        this.trigger('inited');
    }

    function Ueditor(element, options) {
        formControl.apply(this, arguments);
        init.apply(this);
    }

    Ueditor.getNamespace = function () {
        return 'util.formControl.ueditor';
    };

    Ueditor.DEFAULTS = $.extend(Ueditor.DEFAULTS, formControl.prototype.constructor.DEFAULTS, {
        height: 400,
        uploadName: '',
        ueConfig: {}
    });

    delete Ueditor.DEFAULTS.value;

    Ueditor.prototype = $.extend({}, formControl.prototype);
    Ueditor.prototype.constructor = Ueditor;

    Ueditor.prototype.getDefaults = function () {
        return Ueditor.DEFAULTS;
    };

    Ueditor.prototype.isMultiControl = function () {
        return true;
    };

    Ueditor.prototype.getExtraValue = function () {
        var obj = {},
            val = this.getValue(),
            $temp = $(val),
            vals = [];

        $temp.find('img[upload],video[upload]').each(function () {
            var tagName = this.tagName.toUpperCase(),
                src,
                $this = $(this);

            if (tagName === 'IMG') {
                src = $this.attr('src');
                if (!src) src = $this.prop('src');
                src && vals.push(src);
            } else if (tagName === 'VIDEO') {
                src = $this.attr('src');
                if (!src) src = $this.prop('src');
                if (!src) {
                    $this.find('source').each(function () {
                        if (!src) {
                            src = $(this).attr(src);
                            if (!src) src = $(this).prop('src');
                        }
                    });
                }
                src && vals.push(src);
            }
        });
        obj[this.getUploadName()] = vals.length ? vals.join(',') : '';
        return obj;
    };

    Ueditor.prototype.getUploadName = function () {
        return this.uploadName;
    };

    Ueditor.prototype.setValue = function (value) {
        var $element = this.$element,
            value = util.isEmpty(value) ? '' : value;

        if (value === this.getValue()) return;
        setInputValue.call(this, value);
        $element.trigger('change');
    };

    Ueditor.prototype.getValue = function () {
        return this.$element.val();
    };

    Ueditor.prototype.disable = function () {
        this.ue && this.ueReady && this.ue.setDisabled();
    };

    Ueditor.prototype.enable = function () {
        this.ue && this.ueReady && this.ue.setEnabled();
    };

    Ueditor.prototype.reset = function () {
        setInputValue.call(this, this.initValue);
    };

    _ueditor = function (selector, option) {
        var obj = [],
            ns = Ueditor.getNamespace();

        $(selector).each(function () {
            var $this = $(this),

                data = $this.data(ns),
                options = typeof option == 'object' && option;

            if (!data) $this.data(ns, (data = new Ueditor(this, options)));

            obj.push(data);
        });

        if (obj.length == 1) return obj[0];
        return obj;
    };

    $.extend(formControl, {
        ueditor: _ueditor
    });

}(window, jQuery);
/**
 * author zengxf
 * added 2015-09-09
 */
+function (_, $) {


    var util = _.util || {},
        formControl = util.formControl = util.formControl || {},
        _upload,
        ue,
        ueConfig = {},
        current,
        ifRequired = function ($element) {
            var required = $element.attr('required') + '';
            return ('required' === required || 'true' === required);
        },
        _getBtnTooltip = function ($element, maxSize, fixSize) {
            if (maxSize === 0) return '';

            var required = ifRequired($element);

            if (fixSize) {
                if (required) {
                    return '提示：必须上传' + maxSize + this.unit + this.text + '！';
                } else {
                    return '提示：如果上传，则必须上传' + maxSize + this.unit + this.text + '！';
                }
            } else {
                if (required) {
                    return '提示：最多上传' + maxSize + this.unit + this.text + '！';
                } else {
                    return '提示：如果上传，则最多上传' + maxSize + this.unit + this.text + '！';
                }
            }
        },
        _getSizeErrorMessage = function ($element, maxSize) {
            return '最多上传' + maxSize + this.unit + this.text + '，已超出上传限制，请重新上传！';
        },
        _getUploadItemValue = function (item) {
            return item.url || item.src;
        },
        uploadMap = {
            file: {
                text: '文件',
                unit: '个',
                btnText: '上传',
                eventType: 'afterUpfile',
                dialogType: 'attachment',
                dialogTitle: '',
                btnIcon: 'fa fa-upload',
                getBtnTooltip: _getBtnTooltip,
                getUploadItemValue: _getUploadItemValue,
                getSizeErrorMessage: _getSizeErrorMessage
            },
            video: {
                text: '视频',
                unit: '个',
                btnText: '上传',
                eventType: 'afterUpVideo',
                dialogType: 'insertvideo',
                dialogTitle: '',
                btnIcon: 'fa fa-video-camera',
                getBtnTooltip: _getBtnTooltip,
                getUploadItemValue: _getUploadItemValue,
                getSizeErrorMessage: _getSizeErrorMessage
            },
            audio: {
                text: '音频',
                unit: '个',
                btnText: '上传',
                eventType: 'afterUpVideo',
                dialogType: 'insertvideo',
                dialogTitle: '音频',
                btnIcon: 'fa fa-music',
                getBtnTooltip: _getBtnTooltip,
                getUploadItemValue: _getUploadItemValue,
                getSizeErrorMessage: _getSizeErrorMessage
            },
            image: {
                text: '图片',
                unit: '张',
                btnText: '上传',
                eventType: 'afterUpImage',
                dialogType: 'insertimage',
                dialogTitle: '',
                btnIcon: 'fa fa-image',
                getBtnTooltip: _getBtnTooltip,
                getUploadItemValue: _getUploadItemValue,
                getSizeErrorMessage: _getSizeErrorMessage
            }
        },
        uploadListener = function (t, arg) {
            var filePath = [],
                count = arg && arg.length,
                uploadType = current.uploadType,
                controlType = uploadMap[uploadType],
                val,
                totalCount = count,
                opts = current.options,
                maxSize = opts.fvUploadMaxSize;

            for (var i = 0; i < count; i++) {
                filePath.push(controlType.getUploadItemValue(arg[i]));
            }

            val = current.getValue();
            totalCount += current.getUploadedLength();

            if (maxSize && maxSize < totalCount) {
                current.showMessage(controlType.getSizeErrorMessage(current.getElement(), maxSize), 'error');
            } else if(count > 0) {
                current.showMessage('成功上传' + count + controlType.unit + controlType.text + '！', 'success');
                if (val !== '') {
                    val += ',';
                }
                val += filePath.join(',');
                current.setValue(val);
            }
        },
        addUeListener = function (eventType) {
            ue.addListener(eventType, uploadListener);
        },
        removeUeListener = function (eventType) {
            ue.removeListener(eventType, uploadListener);
        };

    //初始化ue，所有上传组件ue对象只初始化一次
    function setUpUe() {
        var editorId = 'global-upload-editor',
            editorName = 'global-upload-text',
            initHtml = [
                '<div style="height:0;overflow:hidden;"><script id="'
                , editorId
                , '" name="'
                , editorName
                , '" type="text/plain" style="width:0;height:0"></script>'
                , '</div>'
            ].join('');

        $(document.body).prepend(initHtml);

        ue = UE.getEditor(editorId, ueConfig);
        ue.customUploadType = true;

        ue.ready(function () {
            ue.setDisabled();
            ue.hide();
        });
    }

    function bindEvents() {
        var opts = this.options,
            that = this,
            $element = this.$element,
            $btn = this.$btn,
            uploadType = this.uploadType;

        $btn.on('click', function (e) {
            current = that;

            if (opts.fvUploadMaxSize && opts.fvUploadMaxSize === that.getUploadedLength()) {
                that.showMessage('已达上传数量限制！', 'error');
                return;
            }

            var controlType = uploadMap[uploadType],
                dialog = ue.getDialog(controlType.dialogType),
                eType = controlType.eventType;
            if (controlType.dialogTitle) dialog.title = controlType.dialogTitle;

            removeUeListener(eType);
            addUeListener(eType);

            ue.customConfig = opts.config || '';
            ue.customType = uploadType;
            dialog.open();
        });

        $element.on('change.' + Upload.getNamespace(), function (e) {
            var val = that.getValue();

            if (!that.trigger('beforeChange', val)) {
                setInputValue.call(that, that.lastValue);
                return;
            }
            that.lastValue = val;
            that.trigger('changed', val);
        });

        if (typeof(opts.onChange) === 'function') {
            this.on('changed', opts.onChange);
        }

        if (typeof(opts.onBeforeChange) === 'function') {
            this.on('beforeChange', opts.onBeforeChange);
        }
    }


    function setUploadType() {
        var opts = this.options;
        this.uploadType = opts.uploadType || 'file';

        delete opts.uploadType;
    }

    function createBtn() {
        var uploadType = this.uploadType,
            opts = this.options,
            $element = this.$element,
            tooltip = uploadMap[uploadType].getBtnTooltip($element, opts.fvUploadMaxSize, opts.fvUploadFixedSize),
            $btn = $([
                '<button type="button" class="btn btn-primary btn-sm" style="float: left;margin-right: 10px;"'
                , tooltip ? ' data-toggle="tooltip" data-placement="top" title="' + tooltip + '"' : ''
                , '><i class="'
                , uploadMap[uploadType].btnIcon
                , '"></i> '
                , uploadMap[uploadType].btnText + uploadMap[uploadType].text
                , '</button>'
            ].join(''));

        this.$element.before($btn);
        this.$btn = $btn;
    }

    function createMessage() {
        var $message = $('<p class="upload-message"></p>');
        this.$message = $message;
        this.$element.before($message);
    }


    function setInputValue(value) {
        this.$element.val(value);
    }

    function setInitValue(value) {
        setInputValue.call(this, value);
        this.lastValue = value;
    }

    function init() {
        setUploadType.apply(this);
        createBtn.apply(this);
        createMessage.apply(this);
        bindEvents.apply(this);
        setInitValue.call(this, this.initValue);
        this.$element.prop('name', this.name);
        !ue && setUpUe.apply(this);
        this.trigger('inited');
    }

    function Upload(element, options) {
        formControl.apply(this, arguments);
        init.apply(this);
    }

    Upload.getNamespace = function () {
        return 'util.formControl.upload';
    };

    Upload.DEFAULTS = $.extend(Upload.DEFAULTS, formControl.prototype.constructor.DEFAULTS, {
        uploadType: 'file',
        config: '',
        fvUploadMaxSize: 0,//最多上传多少个文件，为0时不限制
        fvUploadFixedSize: false,//是否必须上传fvUploadMaxSize指定个数的文件,
        messageDuration: 2000
    });

    delete Upload.DEFAULTS.value;

    Upload.prototype = $.extend({}, formControl.prototype);
    Upload.prototype.constructor = Upload;

    Upload.prototype.getDefaults = function () {
        return Upload.DEFAULTS;
    };

    Upload.prototype.setValue = function (value) {
        var $element = this.$element,
            value = util.isEmpty(value) ? '' : value;

        if (value === this.getValue()) return;
        setInputValue.call(this, value);
        $element.trigger('change');
    };

    Upload.prototype.showMessage = function (value, css) {
        var opts = this.options,
            $message = this.$message;

        this.messageTimer && clearTimeout(this.messageTimer);

        $message.text('').removeClass('fade').removeClass('in').removeClass(css);
        setTimeout(function () {
            $message.text(value).addClass('fade').addClass(css).addClass('in');
        }, 0);

        this.messageTimer = setTimeout(function () {
            $message.text('').removeClass('fade').removeClass('in').removeClass(css);
        }, opts.messageDuration);
    };

    Upload.prototype.getValue = function () {
        return this.$element.val();
    };

    Upload.prototype.getUploadedLength = function () {
        var val = this.getValue();
        if (val === '') return 0;
        else return val.split(',').length;
    };

    Upload.prototype.disable = function () {
        this.$btn.prop('disabled', true);
    };

    Upload.prototype.enable = function () {
        this.$btn.prop('disabled', false);
    };

    Upload.prototype.reset = function () {
        setInputValue.call(this, this.initValue);
    };

    _upload = function (selector, option) {
        var obj = [],
            ns = Upload.getNamespace();

        $(selector).each(function () {
            var $this = $(this),

                data = $this.data(ns),
                options = typeof option == 'object' && option;

            if (!data) $this.data(ns, (data = new Upload(this, options)));

            obj.push(data);
        });

        if (obj.length == 1) return obj[0];
        return obj;
    };

    $.extend(formControl, {
        upload: _upload
    });

}(window, jQuery);
/**
 * author zengxf
 * added 2015-05-17
 */
;(function(_,$){
	var util = _.util || {};

	function Gallary(selector, options) {
		this.$container = typeof(selector) == 'string' ? $(selector) : (selector.constructor == $ ? selector : []);
		if (this.$container.length == 0) {
			return;
		}
		this.$container.data('control', this);
		this.options = this.getOptions(options);
		delete this.options.control;

		this.initing();
		this.bindEventHandler();
		this.render();
	}

	Gallary.defaults = {
		name: '',
		type: 'gallary',
		value: '',
		deleteSelector: '.opera-delete',
		showBtn: true
	};

	Gallary.prototype.constructor = Gallary;

	Gallary.prototype.bindEventHandler = function () {
		this.$container.on('click.delete.' + this.options.type, this.options.deleteSelector, $.proxy(this.deleteItem, this));
	};

	Gallary.prototype.render = function () {
		this.trigger('rendering');
		this.beforeRender();
		this.$container.find('.gallary-item').remove();

		var value = this.options.value || '';
		if (value === '') {
			this.$container.closest('.control.min-height').css('height', 0);
			this.$container.removeClass('has-item');
			return;
		}

		var vals = value.split(',');
		var l = vals.length;

		var html = [];
		for (var i = 0; i < l; i++) {
			html.push(['   <div class="gallary-item" data-value="',
				vals[i],
				'">',
				this.beforeItemRender(),
				this.renderGallaryItem(vals[i]),
				this.renderDeleteOperation(),
				this.afterItemRender(),
				'</div>'].join(''));
		}

		this.$container.addClass('has-item');
		this.$container.closest('.control.min-height').css('height', 'auto');
		this.$container.append($(html.join('')));
		this.afterRender();
		this.trigger('rendered');
	};

	Gallary.prototype.initing = function() {

	};


	Gallary.prototype.renderGallaryItem = function(val) {
		return '';
	};

	Gallary.prototype.beforeItemRender = function(val) {
		return '';
	};

	Gallary.prototype.afterItemRender = function() {
		return '';
	};

	Gallary.prototype.afterRender = function(val) {

	};

	Gallary.prototype.beforeRender = function() {

	};

	Gallary.prototype.renderDeleteOperation = function() {
		if(this.options.showBtn) {
			return ['       <div class="gallary-item-operation">',
				'           <label class="opera opera-delete">删除</label>',
				'       </div>'].join('');
		}
		return '';
	};

	Gallary.prototype.deleteItem = function (e) {
		if (!e) return;

		var _this = this;
		var $element = $(e.currentTarget);
		var $item = $element.closest('.gallary-item');

		if (!beforeDelete()) return;

		var val = $item.data('value');
		this.clearValue(val);

		if (!afterDelete()) return;

		function beforeDelete() {
			return _this.trigger('delete', {
				item: $item
			});
		}

		function afterDelete() {
			return _this.trigger('deleted', {
				item: $item
			});
		}
	};

	Gallary.prototype.getDefaults = function () {
		return Gallary.defaults;
	};

	Gallary.prototype.getOptions = function (options) {
		return $.extend({}, this.getDefaults(), this.$container.data(), options || {});
	};

	Gallary.prototype.setValue = function (val) {
		var _this = this;

		if (!beforeChange()) return;

		this.options.value = val;
		this.render();

		if (!afterChange()) return;

		function beforeChange() {
			return _this.trigger('changing', {
				val: val
			});
		}

		function afterChange() {
			return _this.trigger('changed', {
				val: val
			});
		}
	};

	Gallary.prototype.clearValue = function (val) {
		var vals = ( this.options.value || '').split(',');
		var index = vals.indexOf(val);

		if (index < 0) return;

		vals.splice(index, 1);

		this.setValue(vals.join(','));
	};

	Gallary.prototype.addGallaryItem = function (val) {
		var value = this.getValue();
		val = value + (value !== '' ? ',' : '') + (val || '');
		this.setValue(val);
	};

	Gallary.prototype.getValue = function () {
		return this.options.value;
	};

	Gallary.prototype.getContainer = function () {
		return this.$container;
	};

	Gallary.prototype.on = function (type, data, callback) {
		if(arguments.length == 2) {
			callback = data;
            data = undefined;
		}

		if (typeof(callback) != 'function') return;
		this.$container.on(type + '.' + this.options.type, data, callback);
	};

	Gallary.prototype.trigger = function (type, data) {
		var e = $.Event(type + '.' + this.options.type);
		this.$container.trigger(e, data);
		return !e.isDefaultPrevented();
	};

	function ImageGallary(selector, options) {
		Gallary.call(this, selector, options)
	}

	ImageGallary.defaults = $.extend({}, Gallary.defaults, {
		type: 'imageGallary'
	});

	ImageGallary.prototype = $.extend({}, Gallary.prototype);
	ImageGallary.prototype.constructor = ImageGallary;

	ImageGallary.prototype.getDefaults = function () {
		return ImageGallary.defaults;
	};

	ImageGallary.prototype.afterRender = function() {
		this.$container.find('.gallary-item').addClass('image');
		this.$container.find('.fancybox.' + this.options.name).fancybox();
	};

	ImageGallary.prototype.renderGallaryItem = function(val) {
		return ['<div class="gallary-item-wrapper image">',
			'<a class="fancybox ',
			this.options.name,
			'" href="',
			val,
			'"',
			'    data-fancybox-group="gallery"',
			'    title="">',
			'           <img alt="" src="',
			val,
			'"></a>',
			'       </div>'].join('');
	};

	function AtlasGallary(selector, options) {
		Gallary.call(this, selector, options);
	}

	AtlasGallary.defaults = $.extend({}, Gallary.defaults, {
		type: 'atlasGallary',
		validate: true,
		orderOptions: {
			trigger: 'blur keyup',
			validators: {
				notEmpty: {},
				integer: {
					message: '请输入有效的整数类型'
				}
			}
		},
		titleOptions: {
			trigger: 'blur keyup',
			validators: {
				notEmpty: {}
			}
		},
		summaryOptions: {
			trigger: 'blur keyup',
			validators: {
				notEmpty: {}
			}
		}
	});

	AtlasGallary.prototype = $.extend({}, Gallary.prototype);
	AtlasGallary.prototype.constructor = AtlasGallary;

	AtlasGallary.prototype.initing = function() {
		this.titleName = 'appForm-' + this.options.name + '-title-';
		this.summaryName = 'appForm-' + this.options.name + '-summary-';
		this.orderName = 'appForm-' + this.options.name + '-order-';
		this.validate = this.options.validate;
		this.$form = this.$container.closest('form');
	};

	AtlasGallary.prototype.getDefaults = function () {
		return AtlasGallary.defaults;
	};

	AtlasGallary.prototype.getVals = function (value) {
		return JSON.parse(value).filter(function(item) {
			return !item.deleted;
		});
	};

	AtlasGallary.prototype.render = function () {
		this.trigger('rendering');
		this.beforeRender();
		this.$container.find('.gallary-item').remove();

		var value = this.options.value || '';
		if (value === '') {
			this.$container.closest('.control.min-height').css('height', 0);
			this.$container.removeClass('has-item');
			return;
		}

		var vals = this.getVals(value);
		var l = vals.length;

		var html = [];
		for (var i = 0; i < l; i++) {
			vals[i].index=  i;
			html.push(['   <div class="gallary-item" data-id="',
				vals[i].id,
				'" data-value="',
				vals[i].url,
				'">',
				this.beforeItemRender(vals[i]),
				this.renderGallaryItem(vals[i]),
				this.renderDeleteOperation(vals[i]),
				this.afterItemRender(vals[i]),
				'</div>'].join(''));
		}

		this.$container.addClass('has-item');
		this.$container.closest('.control.min-height').css('height', 'auto');
		this.$container.append($(html.join('')));
		this.afterRender();
		this.trigger('rendered');
	};


	AtlasGallary.prototype.afterRender = function() {
		this.$container.find('.gallary-item').addClass('atlas').addClass('clearfix');
		this.$container.find('.fancybox.' + this.options.name).fancybox();
		this.$container.find('[data-toggle="tooltip"]').tooltip();
	};

	AtlasGallary.prototype.beforeItemRender = function(val) {
		return '<div class="atlas-image">';
	};

	AtlasGallary.prototype.resolveValidation = function(e,validate) {
		if(validate === false) return;
		if(!this.validate) return;
		if(!util.form) return;

		var formId = this.$form.attr('id');
		if(!formId) return;

		var value = ( this.options.value || '');
		if(value === '') return;

		var vals = this.getVals(value);
		var l = vals.length;
		var val;
		var $item;
		var $title,$order,$summary;
		var form = util.form.get(formId);
		for(var i = 0; i<l ; i++) {
			val = vals[i];

			$item = this.$container.find('.gallary-item[data-value="' + val.url + '"]');
			$title = $item.find('.form-control.title');
			$order = $item.find('.form-control.order');
			$summary = $item.find('.form-control.summary');

			if(e.data) {
				form.removeValidateField($title.attr('name'));
				form.removeValidateField($order.attr('name'));
				form.removeValidateField($summary.attr('name'));
			} else {
				form.addValidateField($title.attr('name'),this.options.titleOptions);
				form.addValidateField($order.attr('name'),this.options.orderOptions);
				form.addValidateField($summary.attr('name'),this.options.summaryOptions);
			}
		}
	};

	AtlasGallary.prototype.afterItemRender = function(val) {
		return ['</div>',
			'<div class="atlas-inputs">',
			'	<div class="form-group">',
			'   	<label class="col-xs-1 control-label">标题:</label>',
			'   	<div class="col-xs-11">',
			'           <input type="text" data-type="title"',
			' name="',
			this.titleName+ val.index,
			'"',
			'             class="form-control title"',
			'             placeholder="请输入标题..." value="',
			val.title,
			'"/>',
			'       </div>',
			'   </div>',
			'   <div class="form-group">',
			'      <label class="col-xs-1 control-label">摘要:</label>',
			'      <div class="col-xs-11">',
			'         <textarea',
			'            class="form-control summary" data-type="summary"',
			' name="',
			this.summaryName+ val.index,
			'"',
			'            rows="4" ',
			'            placeholder="请输入摘要...">',
			val.summary,
			'</textarea>',
			'      </div>',
			'   </div>',
			'</div>'].join('');
	};

	AtlasGallary.prototype.renderGallaryItem = function(val) {
		return ['<div class="gallary-item-wrapper atlas">',
			'<a class="fancybox ',
			this.options.name,
			'" href="',
			val.url,
			'"',
			'    data-fancybox-group="gallery"',
			'    title="">',
			'           <img alt="" src="',
			val.url,
			'"></a></div>'].join('');
	};

	AtlasGallary.prototype.renderDeleteOperation = function(val) {
		if(this.options.showBtn) {
			return ['       <div class="gallary-item-operation">',
				'           <label class="opera opera-delete">删除</label><div class="form-group opera"><input class="form-control order" ',
				' data-type="order" type="text" data-toggle="tooltip"',
				' data-placement="right"',
				' name="',
				this.orderName+ val.index,
				'"',
				' title="排序值" value="',
				val.order,
				'">',
				'</div></div>'].join('');
		}
		return '';
	};

	AtlasGallary.prototype.clearValue = function (val) {
		var value = ( this.options.value || '');
		if(value === '') return;

		var vals = JSON.parse(value);
		var current = vals.filter(_hasValueFilter);

		if (!current.length) return;
		if(current[0].id !== '') {
			current[0].deleted = true;
		} else {
			vals = vals.filter(_notHasValueFilter);
		}

		this.setValue(vals.length ? JSON.stringify(vals) : '');

		function _hasValueFilter(item) {
			return item.url == val;
		}

		function _notHasValueFilter(item) {
			return !(item.url == val);
		}
	};

	AtlasGallary.prototype.addGallaryItem = function (val) {
		var vals = [];
		var value = ( this.options.value || '');
		if(value != '') {
			vals = vals.concat(JSON.parse(value));
		}

		if(val != '') {
			vals = vals.concat(JSON.parse(val));
		}
		this.setValue(JSON.stringify(vals));
	};

	AtlasGallary.prototype.inputChange = function(e) {
		if(!e) return;

		var $this = $(e.currentTarget);
		var $item = $this.closest('.gallary-item');
		var url = $item.data('value');

		var vals = JSON.parse(this.options.value);
		var current = vals.filter(_filter);
		var type = $this.data('type');
		current[0][type] = type == 'order' ? parseInt($this.val()) : $this.val();

		vals = JSON.stringify(vals);
		this.options.value = vals;
		this.trigger('changed', {
			val: vals
		});

		function _filter(item) {
			return item.url == url;
		}
	};

	AtlasGallary.prototype.bindEventHandler = function() {
		Gallary.prototype.bindEventHandler.apply(this,arguments);
		this.on('changing',true, $.proxy(this.resolveValidation,this));
		this.on('rendered',false, $.proxy(this.resolveValidation,this));
		this.$container.on('change','.form-control.order,.form-control.title,.form-control.summary', $.proxy(this.inputChange,this));
	};


	function FileGallary(selector, options) {
		Gallary.call(this, selector, options)
	}

	FileGallary.defaults = $.extend({}, Gallary.defaults, {
		type: 'fileGallary'
	});

	FileGallary.prototype = $.extend({}, Gallary.prototype);
	FileGallary.prototype.constructor = FileGallary;

	FileGallary.prototype.getDefaults = function () {
		return FileGallary.defaults;
	};

	FileGallary.prototype.afterRender = function() {
		this.$container.find('.gallary-item').addClass('file');
	};

	FileGallary.prototype.renderGallaryItem = function(val) {
		return ['<div class="gallary-item-wrapper file">',
			'<img alt="" src="images/_base/file.png" title="',
			val,
			'">',
			'       </div>'].join('');
	};

	function VideoGallary(selector, options) {
		Gallary.call(this, selector, options)
	}

	VideoGallary.defaults = $.extend({}, Gallary.defaults, {
		type: 'videoGallary',
		size: 3
	});

	VideoGallary.prototype = $.extend({}, Gallary.prototype);
	VideoGallary.prototype.constructor = VideoGallary;

	VideoGallary.prototype.getDefaults = function () {
		return VideoGallary.defaults;
	};

	VideoGallary.prototype.afterRender = function() {
		this.$container.find('.gallary-item').addClass('video');
		this.$container.find('.video-js').each(function(){
				var $this = $(this);
				videojs(this, {}, function(){
					$this.parent().removeClass('hidden');
				});
			}
		);
	};

	VideoGallary.prototype.beforeRender = function() {
		this.$container.find('video').each(function(){
			videojs(this).dispose();
		});
	};

	VideoGallary.prototype.renderGallaryItem = function(val) {
		return ['<div class="gallary-item-wrapper video">',
			'<video class="video-js vjs-big-play-centered vjs-default-skin hidden"',
			'           width="100%" height="100%" controls preload="auto">',
			'        <source src="',
			val,
			'" type="video/mp4" />您的设备不支持HTML5 video',
			'    </video>',
			'       </div>'].join('');
	};

	function AudioGallary(selector, options) {
		Gallary.call(this, selector, options)
	}

	AudioGallary.defaults = $.extend({}, Gallary.defaults, {
		type: 'audioGallary',
		size: 3
	});

	AudioGallary.prototype = $.extend({}, Gallary.prototype);
	AudioGallary.prototype.constructor = AudioGallary;

	AudioGallary.prototype.getDefaults = function () {
		return AudioGallary.defaults;
	};

	AudioGallary.prototype.afterRender = function() {
		var items = this.$container.find('.gallary-item');
		items.addClass('audio');

		items.each(function() {
			var $this = $(this);
			var $rolate = $this.find('.play-rolate');
			var $playControl =  $this.find('.play-control');
			var $audio = $this.find('audio');
			$audio.audioPlayer({onEnd: function(){
				$playControl.toggleClass('pause');
				$rolate.toggleClass('pause');
			}});

			var $playerControl = $this.find('.audio-wrapper .audioplayer-playpause');
			$playControl.click(function(e){
				var $this = $(this);
				$this.toggleClass('pause');
				$rolate.toggleClass('pause');
				$playerControl.trigger('click');
			});
		});
	};

	AudioGallary.prototype.disposeMediaElement = function(el){
		if (!el) { return; }

		if (el.parentNode) {
			el.parentNode.removeChild(el);
		}

		// remove any child track or source nodes to prevent their loading
		while(el.hasChildNodes()) {
			el.removeChild(el.firstChild);
		}

		el.removeAttribute('src');

		if (typeof el.load === 'function') {
			(function() {
				try {
					el.load();
				} catch (e) {
				}
			})();
		}
	};

	AudioGallary.prototype.beforeRender = function() {
		var _this = this;
		this.$container.find('audio').each(function(){
			_this.disposeMediaElement(this);
		});
	};

	AudioGallary.prototype.renderGallaryItem = function(val) {
		return ['<div class="gallary-item-wrapper audio-container audio">',
			'      <img class="bg blur" src="images/_base/play_bg.png" alt="">',
			'      <div class="play-circle-1"></div>',
			'      <div class="play-circle-2"></div>',
			'      <div class="play-circle-3 pause play-rolate">',
			'         <img class="play-bg" src="images/_base/header_bg.png" alt="">',
			'      </div>',
			'      <span class="play-icon play-control"></span>',
			'      <div class="audio-wrapper">',
			'        <audio preload="auto" controls>',
			'           <source src="',
			val,
			'">',
			'        </audio>',
			'      </div>',
			'    </div>'].join("");
	};


	_.util = $.extend(util, {
        gallary: function(type,selector,options) {
			switch (type) {
				case 'image':
					return new ImageGallary(selector, options);
				case 'atlas':
					return new AtlasGallary(selector, options);
				case 'video':
					return new VideoGallary(selector,options);
				case 'file' :
					return new FileGallary(selector,options);
				case 'audio' :
					return new AudioGallary(selector,options);
			}
		}
	});
	
})(window,jQuery);

/**
 * @author 曾险峰
 * 2015-08-20
 */

+function (_, $) {


    var ns = 'mam.info';

    function Info() {
        this.init();
        this.bindEvents();
    }

    Info.DEFAULTS = {
        delay: 1000,
        transitionDur: 300
    };

    Info.prototype.getDefaults = function () {
        return Info.DEFAULTS;
    };

    Info.prototype.init = function () {
        this.$body = $(document.body);
        this.$element = $(_getTemplate());
        this.options = this.getDefaults();
        this.timeout = 0;

        var $element = this.$element;
        var $body = this.$body;
        $element.appendTo($body);// add to dom

        this.$content = $element.find('.content');

        function _getTemplate() {
            return ['<div class="info-dialog">',
                '    <p class="content"></p>',
                '    <span class="fa fa-times info-close" title="关闭"></span>',
                '</div>'].join("");
        }
    };

    Info.prototype.bindEvents = function () {
        this.$element.on('click.' + ns, '.info-close', $.proxy(this.close, this));
    };

    Info.prototype.close = function (e) {
        this.hide();
    };

    Info.prototype.show = function (message) {
        var timeout = this.timeout;
        clearTimeout(timeout);

        this.$content.text(message || '');

        var $element = this.$element;
        var opts = this.options;
        $element.addClass('in');

        var that = this;
        timeout = setTimeout(function(){
            that.hide();
        },opts.delay);

        this.timeout = timeout;
    };

    Info.prototype.hide = function () {
        var $element = this.$element;
        var opts = this.options;
        var that = this;

        function _hide() {
            that.$content.text('');
        }

        $element.removeClass('in');

        $.support.transition ?
            $element
                .one('bsTransitionEnd', _hide)
                .emulateTransitionEnd(opts.transitionDur) :
            _hide();
    };

    var util = _.util || {};

    var info = new Info();

    var _info = function (message) {
        info.show(message);
    };

    _.util = $.extend(util, {
        info: _info
    });


}(window, jQuery);
/**
 * author zengxf
 * added 2015-05-14
 */
;
(function (_, $) {
    var util = _.util || {};

    var BorderLayout = function (bar, left, center, options) {
        this.bar = bar;
        this.left = left;
        this.center = center;
        this.moveable = false;
        this.startDragPos = null;
        this.type = 'borderLayout';
        this.options = $.extend({}, BorderLayout.DEFAULTS, options || {});

        this.leftWidth = left.width();
        bar.on("mousedown." + this.type, $.proxy(this.readyToDrag, this));
    };

    BorderLayout.DEFAULTS = {
        dragArea: {
            max: 250,
            min: 10
        },
        storeWidth: false,
        frameMask: false
    };

    BorderLayout.prototype = {
        readyToDrag: function (e) {
            this.moveable = true;
            this.startDragPos = {
                left: e.pageX,
                top: e.pageY
            };

            e.stopPropagation();

            this.options.frameMask && $('.frame-mask').removeClass('hide');

            $(document).off('.' + this.type).on("mousemove." + this.type, $.proxy(this.dragging, this))
                .on("mouseup." + this.type, $.proxy(this.dragStopped, this))
                .on("selectstart." + this.type, function (e) {
                    e.preventDefault();
                });
        },
        dragStopped: function (e) {
            this.options.frameMask && $('.frame-mask').addClass('hide');
            $(document).off('.' + this.type);
            this.moveable = false;
            this.leftWidth = this.finalWidth;
        },
        dragging: function (e) {
            e.stopPropagation();
            var movePos, offset, width;
            if (!this.moveable) return;

            movePos = {
                left: e.pageX,
                top: e.pageY
            };

            offset = movePos.left - this.startDragPos.left;
            width = this.leftWidth + offset;

            if (width >= this.options.dragArea.min && width <= this.options.dragArea.max) {
                this.left.width(width);
                this.bar.css("left", (width - 1) + "px");
                this.center.css("left", (width + 5) + "px");
                this.finalWidth = width;
                this.options.storeWidth && util.local.set('menuWidth', width);

                if (util.table) {
                    util.table.each(function (t) {
                        t.resize();
                    });
                }
            } else if (width > this.options.dragArea.max) {
                this.finalWidth = this.options.dragArea.max;
            } else if (width < this.options.dragArea.min) {
                this.finalWidth = this.options.dragArea.min;
            }
        }
    };

    var _layout = function (bar, left, center, options) {
        return new BorderLayout(bar, left, center, options);
    };

    _.util = $.extend(util, {
        layout: _layout
    });

    var bar = $('.split');
    bar.each(function () {
        var $this = $(this);
        var _dragArea = {
            min: $this.parent().data('min') || 40,
            max: $this.parent().data('max') || 300
        };
        var left = $this.siblings('.split-container.left'),
            center = $this.siblings('.split-container.right');
        _layout($this, left, center, {dragArea: _dragArea});
    });
})(window, jQuery);

/**
 * author zengxf
 * added 2015-05-14
 */
;
(function (_, $) {
    var util = _.util || {};

    var BorderLayout = function (bar, left, center, options) {
        this.bar = bar;
        this.left = left;
        this.center = center;
        this.moveable = false;
        this.startDragPos = null;
        this.type = 'borderLayout';
        this.options = $.extend({}, BorderLayout.DEFAULTS, options || {});

        this.leftWidth = left.height();
        bar.on("mousedown." + this.type, $.proxy(this.readyToDrag, this));
    };

    BorderLayout.DEFAULTS = {
        dragArea: {
            max: 250,
            min: 10
        },
        storeWidth: false,
        frameMask: false
    };

    BorderLayout.prototype = {
        readyToDrag: function (e) {
            this.moveable = true;
            this.startDragPos = {
                left: e.pageX,
                top: e.pageY
            };

            e.stopPropagation();

            this.options.frameMask && $('.frame-mask').removeClass('hide');

            $(document).off('.' + this.type).on("mousemove." + this.type, $.proxy(this.dragging, this))
                .on("mouseup." + this.type, $.proxy(this.dragStopped, this))
                .on("selectstart." + this.type, function (e) {
                    e.preventDefault();
                });
        },
        dragStopped: function (e) {
            this.options.frameMask && $('.frame-mask').addClass('hide');
            $(document).off('.' + this.type);
            this.moveable = false;
            this.leftWidth = this.finalWidth;
        },
        dragging: function (e) {
            e.stopPropagation();
            var movePos, offset, width;
            if (!this.moveable) return;

            movePos = {
                left: e.pageX,
                top: e.pageY
            };

            offset = movePos.top - this.startDragPos.top;
            width = this.leftWidth + offset;

            if (width >= this.options.dragArea.min && width <= this.options.dragArea.max) {
                this.left.height(width);
                this.bar.css("top", (width - 1) + "px");
                this.center.css("top", (width + 5) + "px");
                this.finalWidth = width;

                if (util.table) {
                    util.table.each(function (t) {
                        t.resize();
                    })
                }
            } else if (width > this.options.dragArea.max) {
                this.finalWidth = this.options.dragArea.max;
            } else if (width < this.options.dragArea.min) {
                this.finalWidth = this.options.dragArea.min;
            }
        }
    };

    var _layoutV = function (bar, left, center, options) {
        return new BorderLayout(bar, left, center, options);
    };

    _.util = $.extend(util, {
        layoutV: _layoutV
    });

    var barV = $('.split-v');
    barV.each(function () {
        var $this = $(this);
        var _dragArea = {
            min: $this.parent().data('min') || 100,
            max: $this.parent().data('max') || 400
        };
        var topV = $this.siblings('.top'),
            bottomV = $this.siblings('.bottom');
        _layoutV($this, topV, bottomV, {dragArea: _dragArea});
    });
})(window, jQuery);

/**
 * author zengxf
 * added 2015-05-14
 */
;
(function (_, $) {
    var util = _.util || {};
    _.util = $.extend(util, {
        local: (function () {
            var store = _.sessionStorage,
                LOCAL = _.LOCAL = {},
                prefix = util.config.system.name + '-',
                set = function (key, value) {
                    key = prefix + key;
                    LOCAL[key] = value;
                    serialize(key);
                },
                get = function (key) {
                    key = prefix + key;
                    return LOCAL[key]
                },
                remove = function (key) {
                    key = prefix + key;
                    delete LOCAL[key];
                    store.removeItem(key);
                },
                serialize = function (key) {
                    store.setItem(key, JSON.stringify(LOCAL[key]));
                };

            Object.keys(store).forEach(function (key) {
                if (key.indexOf(prefix) > -1) {
                    _.LOCAL[key] = JSON.parse(store.getItem(key));
                }
            });

            return {
                clear: function () {
                    _.LOCAL = LOCAL = {};
                    store.clear();
                },
                set: set,
                get: get,
                remove: remove
            }
        })()
    });

})(this, $);
/**
 * @author 曾险峰
 * 2015-08-20
 */

+function(_, $) {


    var ns = 'mam.mask';

    function Mask() {
        this.$mask = $('<div class="modal-backdrop"></div>');
        var $mask = this.$mask;
        $mask.appendTo($(document.body));// add to dom
        this.count = 0;
    }

    Mask.prototype.show = function() {
        if(this.count == 0) {
            this.$mask.show();
        }
        this.count ++;
    };

    Mask.prototype.hide = function() {
        if(this.count == 1) {
            this.$mask.hide();
        }
        this.count --;
    };

    var util = _.util || {};

    var _mask = new Mask();

    _.util = $.extend(util, {
        mask: _mask
    });


}(window, jQuery);
/**
 * @author 曾险峰
 * 2015-08-20
 */

+function (_, $) {


    var ns = 'mam.modal';

    function Modal(options) {
        this.init(options);
    }

    Modal.DEFAULTS = {
        title: '',
        content: '',
        width: 800,
        height: 600,
        distance: 50,
        onConfirm: undefined,
        onClose: undefined,
        onContentReady: undefined,
        onContentChange: undefined,
        onShown: undefined
    };

    Modal.prototype.getDefaults = function () {
        return Modal.DEFAULTS;
    };

    Modal.prototype.getOptions = function (options) {
        var opts = $.extend({}, this.getDefaults(), options);
        return opts;
    };

    Modal.prototype.init = function (options) {
        var opts = this.getOptions(options);
        this.options = opts;
        this.$modal = $(_getTemplate());
        this.$body = $(document.body);

        var $modal = this.$modal;
        var $body = this.$body;
        $modal.appendTo($body);// add to dom

        this.$modalBody = $modal.find('.modal-body');
        this.$title = $modal.find('.modal-title');
        this.$dialog = $modal.find('.modal-dialog');

        $modal.modal();

        this.bindEvents();

        this.reset();

        if (opts.content) {
            this.trigger('contentReady');
        }

        function _getTemplate() {
            return ['<div class="modal modal-modal fade" data-backdrop="false" data-show="false" data-keyboard="false" role="dialog">',
                '  <div class="modal-dialog">',
                '    <div class="modal-content">',
                '      <div class="modal-header">',
                '        <button type="button" class="close btn-close">&times;</button>',
                '        <h4 class="modal-title">',
                opts.title || '',
                '        </h4>',
                '      </div>',
                '      <div class="modal-body">',
                opts.content || '',
                '      </div>',
                '      <div class="modal-footer">',
                '        <button type="button" class="btn btn-primary btn-confirm">确定</button>',
                '        <button type="button" class="btn btn-default btn-close">返回</button>',
                '      </div>',
                '    </div>',
                '  </div>',
                '</div>'].join("");
        }
    };

    Modal.prototype.resetPosition = function () {
        var opts = this.options;
        this.setPosition(opts.width, opts.height);
    };

    Modal.prototype.getWinSize = function () {
        var fullWindowWidth = window.innerWidth;
        if (!fullWindowWidth) { // workaround for missing window.innerWidth in IE8
            var documentElementRect = document.documentElement.getBoundingClientRect();
            fullWindowWidth = documentElementRect.right - Math.abs(documentElementRect.left);
        }
        var fullWindowHeight = window.innerHeight;
        if (!fullWindowHeight) { // workaround for missing window.innerHeight in IE8
            var documentElementRect = document.documentElement.getBoundingClientRect();
            fullWindowHeight = documentElementRect.bottom - Math.abs(documentElementRect.top);
        }

        return {
            width: fullWindowWidth,
            height: fullWindowHeight
        }
    };

    Modal.prototype.setPosition = function (width, height) {
        var winSize = this.getWinSize();
        var opts = this.options;
        if(width >= winSize.width) width = winSize.width - opts.distance;
        if(height >= winSize.height) height = winSize.height - opts.distance;

        this.$dialog.css({
            'width': width + 'px',
            'height': height + 'px',
            'margin-left': '-' + (width / 2) + 'px',
            'margin-top': '-' + (height / 2) + 'px'
        });
    };

    Modal.prototype.reset = function () {
        this.resetPosition();
    };

    Modal.prototype.bindEvents = function () {
        var opts = this.options, that = this;

        this.$modal.on('click.' + ns, '.btn-confirm', $.proxy(this.confirm, this));
        this.$modal.on('click.' + ns, '.btn-close', $.proxy(this.close, this));
        this.$modal.on('shown.bs.modal', function() {
            that.trigger('shown');
        });

        if (typeof(opts.onContentReady) === 'function') {
            this.on('contentReady', opts.onContentReady);
        }
        if (typeof(opts.onConfirm) === 'function') {
            this.on('confirm', opts.onConfirm);
        }
        if (typeof(opts.onClose) === 'function') {
            this.on('close', opts.onClose);
        }
        if (typeof(opts.onContentChange) === 'function') {
            this.on('contentChange', opts.onContentChange);
        }
        if (typeof(opts.onShown) === 'function') {
            this.on('shown', opts.onShown);
        }
    };

    Modal.prototype.confirm = function (e) {
        if (!this.trigger('confirm'))  return;
        this.hide();
    };

    Modal.prototype.close = function (e) {
        if (!this.trigger('close'))  return;
        this.hide();
    };

    Modal.prototype.open = function () {
        var $modal = this.$modal;
        util.mask.show();
        $modal.modal('show');
    };

    Modal.prototype.hide = function () {
        var $modal = this.$modal;
        var that = this;

        $modal.modal('hide');
        $modal.one('hidden.bs.modal', function () {
            util.mask.hide();
            that.reset();
        });
    };

    Modal.prototype.setTitle = function (title) {
        this.$title.text(title);
    };

    Modal.prototype.setContent = function (content, trigger) {
        this.$modalBody.html(content);
        trigger === true && this.trigger('contentChange');
    };

    Modal.prototype.on = function (type, data, callback) {
        if (arguments.length == 2) {
            callback = data;
            data = undefined;
        }

        if (typeof(callback) != 'function') return;
        this.$modal.on(type + '.' + ns, data, callback);
    };

    Modal.prototype.one = function (type, data, callback) {
        if (arguments.length == 2) {
            callback = data;
            data = undefined;
        }

        if (typeof(callback) != 'function') return;
        this.$modal.one(type + '.' + ns, data, callback);
    };

    Modal.prototype.off = function (type) {
        type && this.$modal.off(type);
    };

    Modal.prototype.trigger = function (type, data) {
        var e = $.Event(type + '.' + ns);
        this.$modal.trigger(e, data);
        return !e.isDefaultPrevented();
    };

    var util = _.util || {};

    var _modal = function (options) {
        return new Modal(options);
    };

    _.util = $.extend(util, {
        modal: _modal
    });


}(window, jQuery);
/**
 * author zengxf
 * added 2015-06-08
 */
+function (_, $) {

    var ns = 'util.navTree',
        DEFAULTS = {
            url: '',
            levels: 2,
            selectableLevel: 0,
            defaultParentId: 0,
            textField: 'text',
            valueField: 'value',
            idField: 'id',
            parentIdField: 'parentId',
            autoTreeData: true,
            resouceType: '',//资源类型，用于权限控制
            multiSelect: false,//false为单选，true为多选
            multiSelectType: {//多选有效，Y表示选中时是否影响父子节点，N表示取消选中时是否影响父子节点，ps表示同时影响，p表示只影响父级，s表示只影响子级
                Y: 'ps',
                N: 'ps'
            },
            multiSelectParent: true,//多选有效，表示getValue时是否返回非叶子节点的value
            singleSelectParent: true,//单选有效，表示是否允许选择非叶子节点
            value: '',
            externalResolve: undefined,
            onChange: undefined,//单击回调
            onReady: undefined,//加载完毕回调
            format: undefined,
            alwaysSelect: true,//单选时是否始终选中
            showExpandIcon: true,
            autoload: true,
            useDefaultBehavior: false,
            color: "#428bca",
            expandIcon: 'glyphicon glyphicon-folder-close',
            collapseIcon: 'glyphicon glyphicon-folder-open'
        },
        objects,
        _navTree;


    function NavTree(element, options) {
        var opts;
        this.$tree = $(element);
        opts = this.getOptions(options);
        this.options = opts;
        this.text = '';
        this.value = '';
        this.filter = {};
        this.nodesCount = 0;
        this.subscribeEvents();
        this.addToPermission(opts.resourceType);
        opts.autoload && this.load();
        !opts.showExpandIcon && this.$tree.addClass('hide-expand-icon');
    }

    // extend util.resourceControl for event binding and resource manager
    NavTree.prototype = $.extend({}, util.resourceControl.prototype);
    NavTree.prototype.constructor = NavTree;

    // override eventControl methods
    NavTree.prototype.getElement = function () {
        return this.$tree;
    };

    NavTree.prototype.getNamespace = function () {
        return ns;
    };

    // override resourceControl methods
    NavTree.prototype.addToPermission = function (resourceType) {
        var that = this,
            opts = this.options;

        if (typeof(resourceType) !== 'string') return;

        if (opts.multiSelect) {
            throw new Error('多选navTree不支持配置成权限资源！');
        }

        this.on('changed.resouceChanged', function (e) {
            that.trigger('resouceChanged');
        });

        util.permission.add({
            resourceType: resourceType,
            controlType: 'navTree',
            control: this
        });
    };

    NavTree.prototype.getResourceId = function () {
        var val = this.getValue();
        if (val) {
            return val;
        }
    };

    // add self methods
    NavTree.prototype.getOptions = function (options) {
        var opts = $.extend({}, DEFAULTS, this.$tree.data(), options);
        if (typeof(opts.multiSelectType) === 'string') {
            opts.multiSelectType = JSON.parse(opts.multiSelectType.replace(/'/g, '"'));
        }
        return opts;
    };

    NavTree.prototype.getTreeOptions = function () {
        var o = {},
            opts = this.options;

        if (opts.expandIcon) {
            o.expandIcon = opts.expandIcon;
        }
        if (opts.collapseIcon) {
            o.collapseIcon = opts.collapseIcon;
        }
        return $.extend({
            showBorder: false,
            color: opts.color,
            levels: opts.levels,
            showCheckbox: opts.multiSelect,
            multiSelect: false
        }, o);
    };

    NavTree.prototype.getText = function () {
        return this.text;
    };

    NavTree.prototype.getValue = function () {
        return this.value;
    };

    NavTree.prototype.subscribeEvents = function () {
        var opts = this.options;
        if (typeof(opts.onChange) === 'function') {
            this.on('changed', opts.onChange);
        }
        if (typeof(opts.onReady) === 'function') {
            this.on('ready', opts.onReady);
        }
    };

    NavTree.prototype.destroy = function () {
        var treeview = this.$tree.data('treeview');
        if (treeview) {
            treeview.remove();
            this.$tree.html('');
        }
    };

    NavTree.prototype.getNodesCount = function () {
        return this.nodesCount;
    };

    NavTree.prototype.refresh = function () {
        this.refreshing = true;
        this.load();
    };

    NavTree.prototype.load = function (params, append) {
        var opts = this.options,
            that = this,
            url = opts.url,
            $tree;

        if (!url || this.loading) return;

        this.loading = true;
        updateFilter(this, params, append);
        $tree = that.$tree;
        this.destroy();
        this.nodesCount = 0;
        this.resetAjaxLoadStatus();

        //var start = new Date().getTime();
        util.ajax().get(url, this.filter).success(function (res) {
            if (res.code == 200) {
                _loadData(res.data);
            } else {
                util.log(res.message);
            }
        }).error(function (res) {
            util.log(res);
        });

        function _loadData(data) {
            //var during = new Date().getTime();
            //console.log('请求时间： ' + (during - start));
            that.setAjaxLoadStatus();
            if (typeof(data) != 'object' || data.constructor != Array || data.length === 0) {
                that.loading = false;
                that.refreshing = false;
                that.trigger('ready', that);
                that.text = '';
                that.value = '';
                return;
            }

            that.nodesCount = data.length;

            if (typeof(opts.externalResolve) === 'function') {
                data = opts.externalResolve(data);
            }

            if (!opts.autoTreeData && opts.parentIdField) {
                data = parseTreeData([], data, opts.idField, opts.parentIdField, opts.defaultParentId);
            }

            parseDataFields(data, opts.textField, opts.valueField, DEFAULTS.textField, DEFAULTS.valueField, 1);

            var value = that.refreshing ? that.value : opts.value,
                initVals = value.split(','),
                treeOpts = that.getTreeOptions(),
                multiSelectType = opts.multiSelectType,
                showCheckbox = treeOpts.showCheckbox,
                ifChecked = showCheckbox && (multiSelectType.Y == 'p' || multiSelectType.Y == 'ps'),
                params = {
                    multiSelect: showCheckbox,
                    singleSelectParent: opts.singleSelectParent,
                    vals: initVals,
                    alwaysSelect: opts.alwaysSelect,
                    useDefaultBehavior: opts.useDefaultBehavior,
                    ifCheckParent: ifChecked,
                    selectableLevel: opts.selectableLevel
                },
                needSelectFirst;

            resolveTreeData(data, [], params);

            needSelectFirst = !treeOpts.showCheckbox && value === '' && !opts.useDefaultBehavior;
            //当initVal为空，并且是单选模式时，设置第一个默认选中
            if (needSelectFirst) {
                setFirstSelectedNode(data);
            }

            $tree.treeview($.extend(treeOpts, {data: data}));

            _updateTextValue($tree.data('treeview'), that);

            if (treeOpts.showCheckbox) {
                _bindMultiSelectHandler();
            } else {
                _bindSingleSelectHandler();
            }

            if (needSelectFirst) {
                that.trigger('changed', that);//触发onSelect监听器执行，但不是通过nodeSelected事件。
            }

            that.loading = false;
            that.refreshing = false;

            //var end = new Date().getTime();
            //console.log('渲染时间： ' + (end - during));

            that.trigger('ready', that);
        }

        function _bindMultiSelectHandler() {
            var type = opts.multiSelectType,
                treeview = $tree.data('treeview');

            $tree.on('nodeSelected', function (e, node) {
                treeview.checkNode(node.nodeId);
            });

            $tree.on('nodeUnselected', function (e, node) {
                e.preventDefault();
                treeview.uncheckNode(node.nodeId);
            });

            $tree.on('nodeChecked ', function (e, node) {
                if (type.Y == 'p' || type.Y == 'ps') {
                    var currentNode = node,
                        parentNode = treeview.getParent(currentNode.nodeId);
                    while (parentNode) {
                        var state = parentNode.state;
                        if (!state.disabled && !state.checked) {
                            treeview.checkNode(parentNode.nodeId, {silent: true});
                        }

                        currentNode = parentNode;
                        parentNode = treeview.getParent(currentNode.nodeId);
                    }
                }

                if (type.Y == 's' || type.Y == 'ps') {
                    _checkAllChildNode(node.nodes);
                }

                _updateTextValue(treeview, that);
                that.trigger('changed', that);

                function _checkAllChildNode(childNodes) {
                    var node;
                    if (childNodes && childNodes.length > 0) {
                        for (var i = childNodes.length - 1; i >= 0; i--) {
                            node = childNodes[i];
                            var state = node.state;
                            if (!state.disabled && !state.checked) {
                                treeview.checkNode(node.nodeId, {silent: true});
                                _checkAllChildNode(node.nodes);
                            }
                        }
                    }
                }
            });

            $tree.on('nodeUnchecked ', function (e, node) {
                if (type.N == 'p' || type.N == 'ps') {
                    var currentNode = node,
                        parentNode = treeview.getParent(currentNode.nodeId);
                    while (parentNode) {
                        var childNodes = parentNode.nodes;
                        var flag = true;
                        for (var i = childNodes.length - 1; i >= 0; i--) {
                            var temp = childNodes[i];
                            if (temp != currentNode && temp.state.checked) {
                                flag = false;
                            }
                        }
                        if (!flag) {
                            break;
                        } else {
                            var state = parentNode.state;
                            if (!state.disabled && state.checked) {
                                treeview.uncheckNode(parentNode.nodeId, {silent: true});
                            }

                            currentNode = parentNode;
                            parentNode = treeview.getParent(currentNode.nodeId);
                        }
                    }
                }
                if (type.N == 's' || type.N == 'ps') {
                    _uncheckAllChildNode(node.nodes);
                }

                _updateTextValue(treeview, that);
                that.trigger('changed', that);

                function _uncheckAllChildNode(childNodes) {
                    var node;
                    if (childNodes && childNodes.length > 0) {
                        for (var i = childNodes.length - 1; i >= 0; i--) {
                            node = childNodes[i];
                            var state = node.state;
                            if (!state.disabled && state.checked) {
                                treeview.uncheckNode(node.nodeId, {silent: true});
                            }
                            _uncheckAllChildNode(node.nodes);
                        }
                    }
                }
            });
        }

        function _bindSingleSelectHandler() {
            var treeview = $tree.data('treeview');
            $tree.on('nodeSelected', function (e, node) {
                _updateTextValue(treeview, that);
                that.trigger('changed', that);
            });

            $tree.on('nodeUnselected', function (e, node) {
                e.preventDefault();
                _updateTextValue(treeview, that);
                that.trigger('changed', that);
            });
        }
    };

    NavTree.prototype.getPropertyValue = function (propName) {
        if (!propName) return;
        var treeview = this.$tree.data('treeview'),
            opts = this.options,
            selectNodes,
            i,
            l,
            arrValue;
        if (opts.multiSelect) {
            selectNodes = treeview.getChecked();
            if (!opts.multiSelectParent) {
                selectNodes = selectNodes.filter(function (elem) {
                    return elem.nodes ? false : true;
                });
            }
        } else {
            selectNodes = treeview.getSelected();
        }

        l = selectNodes.length;
        arrValue = [];
        for (i = 0; i < l; i++) {
            arrValue.push(selectNodes[i][propName]);
        }

        return arrValue.join(',');
    };

    function updateFilter(that, params, append) {
        var oldFilter = that.filter, filter;
        if (params) {
            if (append === false) {
                filter = params;
            } else {
                filter = $.extend({}, oldFilter, params);
            }
            that.filter = filter;
        }
    }

    function _updateTextValue(treeview, that) {
        var opts = that.options,
            selectNodes,
            i,
            l,
            arrText,
            arrValue;
        if (opts.multiSelect) {
            selectNodes = treeview.getChecked();
            if (!opts.multiSelectParent) {
                selectNodes = selectNodes.filter(function (elem) {
                    return elem.nodes ? false : true;
                });
            }
        } else {
            selectNodes = treeview.getSelected();
        }

        l = selectNodes.length;
        arrText = [];
        arrValue = [];

        for (i = 0; i < l; i++) {
            arrText.push(selectNodes[i].text);
            arrValue.push(selectNodes[i].value);
        }

        that.text = arrText.join(',');
        that.value = arrValue.join(',');
    }

    function setFirstSelectedNode(data) {
        var node,
            i,
            l = data.length,
            node;

        for (i = 0; i < l; i++) {
            node = data[i];
            if (node.selectable) {
                node.state.selected = true;
                return true;
            } else if (node.nodes && node.nodes.constructor == Array) {
                if (setFirstSelectedNode(node.nodes)) {
                    return true
                }
            }
        }
        return false;
    }

    function parseDataFields(data, textField, valueField, dTextField, dValueField, level) {
        var l = data.length, t, nodes;

        for (var i = 0; i < l; i++) {
            t = data[i];
            t.level = level;
            if (textField !== dTextField) {
                t[dTextField] = t[textField];
            }
            if (valueField !== dValueField) {
                t[dValueField] = t[valueField];
            }
            nodes = t.nodes;

            if (nodes && typeof(nodes) == 'object' && nodes.constructor === Array) {
                parseDataFields(nodes, textField, valueField, dTextField, dValueField, level + 1);
            }
        }
    }


    function parseTreeData(newData, data, idField, parentIdField, parentId) {
        var l,
            temp;

        temp = data.filter(function (e) {
            return e[parentIdField] == parentId;
        });

        l = temp.length;
        for (var i = 0; i < l; i++) {
            temp[i].nodes = [];
            parseTreeData(temp[i].nodes, data, idField, parentIdField, temp[i][idField]);
            newData.push(temp[i]);
        }
        return newData;
    }

    function resolveTreeData(data, pathNodes, params) {
        var node,
            i,
            l = data.length,
            state,
            hasChilds;

        for (i = 0; i < l; i++) {
            node = data[i];
            resolveNode(node, params);
            pathNodes.push(node);

            state = node.state;
            hasChilds = node.nodes && node.nodes.constructor == Array;

            if (hasChilds) {
                resolveTreeData(node.nodes, pathNodes, params);
            }

            if (state.selected || state.checked) {
                setPathNodesState(pathNodes, params.ifCheckParent);
            }

            pathNodes.pop();
        }
    }

    function resolveNode(node, params) {
        var multiSelect = params.multiSelect,
            selectableLevel = params.selectableLevel,
            singleSelectParent = params.singleSelectParent,
            vals = params.vals,
            alwaysSelect = params.alwaysSelect,
            useDefaultBehavior = params.useDefaultBehavior,
            childNodes,
            hasChildren;

        if (node.nodes && node.nodes.length == 0) {
            delete node.nodes;
        }

        node.alwaysSelect = alwaysSelect && !multiSelect;

        childNodes = node.nodes;
        hasChildren = childNodes && childNodes.constructor == Array && childNodes.length > 0;

        node.state = node.state || {};

        if (multiSelect) {
            node.selectable = hasChildren ? false : true;

            if (!useDefaultBehavior) {
                node.state.checked = vals.filter(function (e) {
                    return e == node.value;
                }).length > 0 ? true : false;
            }
        } else {
            node.selectable = !singleSelectParent && hasChildren ? false : true;
            if (!singleSelectParent && node.level < selectableLevel) {
                node.selectable = false;
            }

            if (!useDefaultBehavior) {
                node.state.selected = node.value == vals[0] ? true : false;
            }
        }
        if (!node.selectable) {
            node.color = 'gray';
        } else {
            node.color = '';
        }
    }

    function setPathNodesState(pathNodes, ifCheckParent) {
        var l = pathNodes.length,
            i,
            node,
            state;

        for (i = 0; i < l; i++) {
            node = pathNodes[i];
            state = node.state;
            if (node.nodes && node.nodes.length) {
                state.expanded = true;
                if (ifCheckParent) {
                    state.checked = true;
                }
            }
        }
    }

    objects = {};
    _navTree = function (selector, option) {
        var obj = [];

        $(selector).each(function () {
            var that = $(this),
                data = that.data('util.navTree'),
                options = typeof option == 'object' && option,
                id = util.generateId(that, 'navTree-');

            if (!data) {
                data = new NavTree(this, options);
                that.data('util.navTree', data);

                objects[id] = data;
            }
            obj.push(data);
        });

        if (obj.length == 1) return obj[0];
        return obj;
    };

    $.extend(_navTree, {
        getTrees: function () {
            return objects;
        },
        get: function (id) {
            if (!id) {
                for (var i in objects) {
                    if (objects.hasOwnProperty(i)) {
                        return objects[i];
                    }
                }
            }
            return objects[id];
        },
        remove: function (id) {
            delete objects[id];
        },
        each: function (callback) {
            var t;
            for (var i in objects) {
                if (!objects.hasOwnProperty(i)) {
                    continue;
                }
                t = objects[i];
                callback(t);
            }
        }
    });

    _.util = $.extend(util, {
        navTree: _navTree
    });
}
(window, jQuery);

/**
 * author zengxf
 * added 2015-06-10
 */
+(function (_, $) {

    var util = _.util || {};
    _.util = $.extend(util, {
        permission: (function () {
            var url = util.config.system.permissionUrl,
                $body = $(document.body),
                resourceTypes = {
                    count: 0
                },
                btnSelector = 'button[data-permission],a[data-permission]',
                settings = {
                    systemCode: util.config.system.name,
                    menu: $body.data('menu') || ''
                },
                _getEnableSelector = function (functionPmsName) {
                    return ['button[data-permission="',
                        functionPmsName,
                        '"],a[data-permission="',
                        functionPmsName,
                        '"]'].join('');
                },
                _checkSetting = function () {
                    if (settings.menu === '') {
                        return false;
                    }
                    return true;
                },
                _refreshPermission = function (data) {
                    var enableSelector = [];

                    $.each(data, function (i, e) {
                        enableSelector.push(_getEnableSelector(e.functionPmsName));
                    });
                    util.button.enable(enableSelector.join(','));
                },
                _queryPermission = function (resources) {
                    util.button.disable(btnSelector);

                    var data, _settings;
                    if (resources) {
                        data = {
                            data: JSON.stringify(resources)
                        }
                    }

                    _settings = $.extend({}, settings);
                    _settings.menuCode = _settings.menu;
                    delete _settings.menu;

                    util.ajax().get(url, $.extend(_settings, data || {})).success(function (res) {
                        if (res.code == 200) {
                            _refreshPermission(res.data);
                        } else {
                            util.log('权限控制：' + res.message);
                        }
                    });
                },
                _resouceChanged = function (e) {
                    var resourceCtrls = [], validCount, readyCount;

                    Object.keys(resourceTypes).forEach(function (resourceType) {
                        var resource, control;
                        if (resourceType !== 'count') {
                            resource = resourceTypes[resourceType];
                            control = resource.control;

                            if (control.isValidResourceControl()) {
                                resourceCtrls.push({
                                    resourceType: resourceType,
                                    resourceId: control.getResourceId(),
                                    ready: control.isAjaxLoaded()
                                });
                            }
                        }
                    });

                    validCount = resourceCtrls.length;
                    if (validCount) {

                        resourceCtrls = resourceCtrls.filter(function (e) {
                            return e.ready;
                        });

                        //只有当所有资源ready之后才去获取权限
                        readyCount = resourceCtrls.length;
                        if (readyCount === validCount) {
                            _queryPermission($.map(resourceCtrls, function (e) {
                                return {
                                    resourceType: e.resourceType,
                                    resourceId: e.resourceId
                                };
                            }));
                        }
                    }
                },
                _set = function (option) {
                    if (typeof(option) === 'object') {
                        settings = $.extend(settings, option);
                    }
                },
                _add = function (obj) {
                    if (!_checkSetting()) return;

                    var resourceType = obj.resourceType,
                        controlType = obj.controlType,
                        control = obj.control,
                        resource;

                    if (!(resourceType in resourceTypes)) {
                        resource = {
                            controlType: controlType,
                            control: control
                        };
                        resourceTypes.count++;
                        resourceTypes[resourceType] = resource;
                        control.on('resouceChanged', _resouceChanged);
                    }
                },
                _remove = function (resourceType) {
                    if (!_checkSetting()) return;

                    var resource;
                    if (resourceType in resourceTypes) {
                        resource = resourceTypes[resourceType];
                        resource.control.off('resouceChanged');
                        delete resourceTypes[resourceType];
                        resourceTypes.count--;
                    }
                };

            $(function () {
                //没有资源类型直接根据菜单获取权限
                if (resourceTypes.count == 0 && _checkSetting()) {
                    _queryPermission();
                }
            });

            return {
                set: _set,
                add: _add,
                remove: _remove
            };
        })()
    });

})(window, jQuery);

/**
 * author zengxf
 * added 2015-06-10
 */
;
(function (_, $) {

    var ns = 'mam.query';

    function Query(selector, options) {
        this.$container = $(selector);
        this.init(options);
    }

    Query.DEFAULTS = {
        autoQueryOnClear: false,
        formOption: {},
        onQuery: undefined,
        onClear: undefined
    };

    Query.prototype.getDefaults = function() {
        return Query.DEFAULTS;
    };

    Query.prototype.getOptions = function (options) {
        var opts = $.extend({}, this.getDefaults(), options);
        return opts;
    };

    Query.prototype.init = function (options) {
        var opts = this.getOptions(options);
        this.options = opts;
        var $container = this.$container;

        this.$relatedTable = $container.next('.dataTable-container');
        this.$expandBtn = $container.find('button[name=btn-expand-query]');
        this.expanded = false;

        var $form = $container.find('.query-form');
        var formId = util.generateId($form,'query-form-');

        this.$form = $form;
        this.form = util.form('#' + formId, $.extend({type: 'query'}, opts.formOption));

        $container.on('click.query.' + ns, 'button[name=btn-default-query]', $.proxy(this.query, this));
        $container.on('click.clear.' + ns, 'button[name=btn-default-clear]', $.proxy(this.clear, this));
        $container.on('click.expand.' + ns, 'button[name=btn-expand-query]', $.proxy(this.toggle, this));
    };

    Query.prototype.query = function () {
        if (!this.form.validation.validate()) return;
        var queryData = this.form.getFormData();

        var opts = this.options;
        typeof(opts.onQuery) === 'function' && opts.onQuery(queryData,this);
    };

    Query.prototype.clear = function () {
        var form = this.form;

        form.reset();
        var queryData = form.getFormData();

        var opts = this.options;
        opts.autoQueryOnClear &&
        typeof(opts.onQuery) === 'function' && opts.onQuery(queryData,this);
    };

    Query.prototype.toggle = function () {
        var $fa = this.$expandBtn.find('.fa');
        var expanded = this.expanded;
        if (!expanded) {
            $fa.removeClass('fa-angle-double-down').addClass('fa-angle-double-up');
        } else {
            $fa.removeClass('fa-angle-double-up').addClass('fa-angle-double-down');
        }

        var $form = this.$form;
        $form.toggleClass('expanded');

        var $table = this.$relatedTable;
        if ($table.length) {
            var queryHeight = $form.outerHeight();
            $table.css('top', queryHeight + 'px');
        }

        this.expanded = !expanded;
    };

    var _query = function (selector, option) {
        var obj = [];

        $(selector).each(function () {
            var $this   = $(this);
            var data    = $this.data('util.query');
            var options = typeof option == 'object' && option;

            if (!data) $this.data('util.query', (data = new Query(this, options)));

            obj.push(data);
        });

        if(obj.length == 1) return obj[0];
        return obj;
    };

    _.util = $.extend(util, {
        query: _query
    });
})(window, jQuery);

/**
 * author zengxf
 * added 2015-09-09
 */
+function (_, $, _u) {


    var ns = 'util.systemSwitcher',
        util = _.util || {},
        _systemSwitcher,
        _template = ['<li class="dropdown">',
            '    <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" role="button" title="切换系统">',
            '        {{curSystemName}} {{#gt1System}}<i class="fa fa-caret-down"></i>{{/gt1System}}',
            '    </a>',
            '    {{#gt1System}}<ul class="dropdown-menu">',
            '        {{#authorizedList}}',
            '           {{#notFirst}}<li role="separator" class="divider"></li>{{/notFirst}}',
            '           <li>',
            '               <a href="{{{url}}}" target="_self">{{system}}</a>',
            '           </li>',
            '        {{/authorizedList}}',
            '    </ul>{{/gt1System}}',
            '</li>'].join("");

    Mustache.parse(_template);

    function resolveResponse(data) {
        var vo = {
            curSystemName: util.config.system.chName,
            gt1System: data.length > 1,
            authorizedList: _u.map(data, function (system, index) {
                return {
                    notFirst: index > 0,
                    url: system.systemHttpUrl,
                    system: system.systemName
                }
            })
        };

        this.$element.removeClass('hidden').html(Mustache.render(_template, vo));
    }

    function SystemSwitcher(element, options) {
        this.$element = $(element);
        this.load();
    }

    SystemSwitcher.prototype = {
        load: function () {
            var that = this;

            util.ajax().get(util.config.system.systemListUrl).success(function (res) {
                if (res.code == 200) {
                    resolveResponse.call(that, res.data || []);
                } else {
                    resolveResponse.call(that, []);
                }
            }).complete(function(){
                $('.dropdown', that.$element).on('show.bs.dropdown', function () {
                    $('.frame-mask').removeClass('hide');
                }).on('hide.bs.dropdown', function () {
                    $('.frame-mask').addClass('hide');
                });
            });
        }
    };

    SystemSwitcher.prototype.constructor = SystemSwitcher;

    _systemSwitcher = function (selector, option) {
        var obj = [];

        $(selector).each(function () {
            var $this = $(this),
                data = $this.data(ns),
                options = typeof option == 'object' && option;

            if (!data) $this.data(ns, (data = new SystemSwitcher(this, options)));

            obj.push(data);
        });

        if (obj.length == 1) return obj[0];
        return obj;
    };

    _.util = $.extend(util, {
        systemSwitcher: _systemSwitcher
    });

}(window, jQuery, _);
/**
 * author zengxf
 * added 2015-06-08
 */
+(function (_, $) {

    function Tab(selector, options) {
        this.$container = $(selector);
        this.init(options);
    }

    Tab.DEFAULTS = {
        onLoad: undefined,
        onClick: undefined
    };

    Tab.prototype.getDefaults = function () {
        return Tab.DEFAULTS;
    };

    Tab.prototype.getOptions = function (options) {
        var opts = $.extend({}, this.getDefaults(), options);
        return opts;
    };

    Tab.prototype.init = function (options) {
        var opts = this.getOptions(options);
        this.options = opts;
        var $container = this.$container;

        this.$tabLis = $container.find('.nav>li');
        this.$tabTriggers = $container.find('.nav>li>a');
        this.$tabPanes = $container.find('.tan-content>.tab-pane');

        var $tabTriggers = this.$tabTriggers;
        var that = this;
        $tabTriggers.each(function () {
            var $this = $(this);
            var index = $this.parent().index();
            if (index > 0) {
                $this.one('shown.bs.tab', index, $.proxy(that.load, that));
            }
            $this.on('shown.bs.tab', index, $.proxy(that.click, that));
        });

        this.load({data: 0});
    };

    Tab.prototype.load = function (e) {
        var opts = this.options;
        typeof(opts.onLoad) === 'function' && opts.onLoad(e.data,this);
    };

    Tab.prototype.click = function (e) {
        var opts = this.options;
        typeof(opts.onClick) === 'function' && opts.onClick(e.data,this);
    };

    Tab.prototype.hide = function (index) {
        this.$tabLis.eq(index).addClass('hidden');
        this.$tabPanes.eq(index).addClass('hidden');
    };

    Tab.prototype.show = function (index) {
        this.$tabLis.eq(index).removeClass('hidden');
        this.$tabPanes.eq(index).removeClass('hidden');
    };

    var _tab = function (selector, option) {
        var obj = [];

        $(selector).each(function () {
            var $this   = $(this);
            var data    = $this.data('util.tab');
            var options = typeof option == 'object' && option;

            if (!data) $this.data('util.tab', (data = new Tab(this, options)));

            obj.push(data);
        });

        if(obj.length == 1) return obj[0];
        return obj;
    };

    _.util = $.extend(util, {
        tab: _tab
    });
})(window, jQuery);

/**
 * @author 曾险峰
 * 2015-08-20
 */

+function (_, $) {


    var ns = 'util.table',
        util = _.util || {},
        $document = $(document),
        $body = $(document.body),
        objects = {};

    function preventSelectStart(type) {
        $document.on('selectstart.' + type, function (e) {
            e.preventDefault();
        });
    }

    function restoreSelectStart(type) {
        $document.off('selectstart.' + type);
    }

    function TableDrag($table, $scrollContainer, $fixedContainer, options) {
        this.$table = $table;
        this.$scrollContainer = $scrollContainer;
        this.$fixedContainer = $fixedContainer;
        this.moveable = false;
        this.startPos = undefined;
        this.curIndex = undefined;
        this.curWidth = undefined;
        this.tdWidthMap = {};
        this.ns = ns + '.' + 'drag';
        this.options = options;

        $table.on('mousedown.' + ns, '.dragger', $.proxy(this.startDrag, this));
    }

    TableDrag.prototype.createDraggers = function () {
        var tdWidthMap = this.tdWidthMap;
        var $fixedContainer = this.$fixedContainer;
        var $scrollContainer = this.$scrollContainer;

        var fixedThs = $fixedContainer.find('th');
        var scrollThs = $scrollContainer.find('th');
        fixedThs.each(function (index, elem) {
            var $this = $(elem);
            if (!$this.is(':last-child') && !$this.is(':first-child')) {
                $this.append($('<span class="dragger"></span>'));
            }
            if (!tdWidthMap[index]) {
                tdWidthMap[index] = $this.outerWidth();
            }

            $this.css('width', tdWidthMap[index] + 'px');
            scrollThs.eq(index).css('width', tdWidthMap[index] + 'px');
        });

        this.fixedThs = fixedThs;
        this.scrollThs = scrollThs;
    };

    TableDrag.prototype.startDrag = function (e) {
        e.stopPropagation();
        $body.addClass('table-dragging');
        this.$table.addClass('drag').addClass('table-dragging');

        this.moveable = true;
        this.startPos = {
            left: e.pageX,
            top: e.pageY
        };

        var $td = $(e.currentTarget).parent();
        this.curIndex = $td.index();
        this.curWidth = $td.outerWidth();

        $document.off('.' + this.ns)
            .on('mousemove.' + this.ns, $.proxy(this.dragging, this))
            .on('mouseup.' + this.ns, $.proxy(this.stopDrag, this));

        preventSelectStart(this.ns);
    };

    TableDrag.prototype.dragging = function (e) {
        e.stopPropagation();
        var movePos, offset;
        if (!this.moveable) return;

        movePos = {
            left: e.pageX,
            top: e.pageY
        };

        var startPos = this.startPos;
        var tdWidthMap = this.tdWidthMap;
        var curIndex = this.curIndex;
        var curWidth = this.curWidth;
        var opts = this.options;
        var dragMax = opts.dragMax;
        var dragMin = opts.dragMin;

        offset = movePos.left - startPos.left;
        offset = offset > dragMax ? dragMax : offset < -1 * dragMax ? -1 * dragMax : offset;

        var finalWidth = curWidth + offset;
        if (finalWidth < dragMin) {
            finalWidth = dragMin
        }
        tdWidthMap[curIndex] = finalWidth;

        this.fixedThs.eq(curIndex).css('width', finalWidth + 'px');
        this.scrollThs.eq(curIndex).css('width', finalWidth + 'px');
    };

    TableDrag.prototype.stopDrag = function (e) {
        $body.removeClass('table-dragging');
        this.$table.removeClass('table-dragging');
        restoreSelectStart(this.ns);
        this.moveable = false;
        $document.off('.' + this.ns);
    };

    var TableTree = (function () {

        var _ns = ns + '.' + 'tree';

        function getTreeFieldTdIndex($table, treeField) {
            var $th = $table.find('th');
            return $th.filter('[data-field="' + treeField + '"]').index();
        }

        function addIndentAndIcon(rows, fieldIndex, treeLevel) {
            rows.each(function () {
                var $row = $(this),
                    treeId = $row.data('treeId') || '',
                    level = treeId.length / 4,
                    rowid = $row.data('id') || '',
                    ifEnd = rows.filter('[data-parent-id="' + rowid + '"]').length ? false : true;

                $row.find('td').eq(fieldIndex).prepend([
                    '<span style="display:inline-block;width:'
                    , ((level - 1) * 40)
                    , 'px;"></span>',
                    , ifEnd ? '' : '<span class="tree-icon ' + (level < treeLevel ? 'expand' : '') + ' glyphicon"></span>'
                ].join(''));

                level > treeLevel && $row.addClass('hidden');
            });
        }

        function bindEvent($element, that) {
            $element.on('click.' + _ns, '.tree-icon', $.proxy(clickHandler, that));
        }

        function clickHandler(e) {
            var $this = $(e.currentTarget),
                $element = this.$element,
                $table = $element.find('.dataTable-body>table'),
                rows = $table.find('tbody>tr'),//所有航
                $row = $this.closest('tr'),//当前行
                treeId = $row.data('treeId') || '',
                rowid = $row.data('id') || '',
                expanded = $this.hasClass('expand'),//当前是否已经展开
                childRows = expanded ?
                    rows.filter('[data-tree-id^="' + treeId + '"]').not($row[0]) :
                    rows.filter('[data-parent-id="' + rowid + '"]');

            //当前展开，则递归隐藏所有后代节点
            //当前收缩，则只展开直接后代节点

            if (expanded) {
                childRows.addClass('hidden');
                childRows.find('.tree-icon').removeClass('expand');
            } else {
                childRows.removeClass('hidden', expanded);
            }
            $this.toggleClass('expand');
        }

        function tree($element, options) {
            this.$element = $element;
            this.options = options;
            this.init();
        }

        tree.prototype = {
            init: function () {
                var opts = this.options,
                    $element = this.$element,
                    $table = $element.find('.dataTable-body>table'),
                    fieldIndex = getTreeFieldTdIndex($table, opts.treeField),
                    rows = $table.find('tbody>tr');

                addIndentAndIcon(rows, fieldIndex, opts.treeLevel);
                bindEvent($element, this);
            },
            destroy: function () {
                this.$element.off('click.' + _ns);
            }
        };

        tree.prototype.constructor = tree;

        return tree;
    })();

    function Table(element, options) {
        var $table = $(element);

        this.$table = $table;
        this.options = this.getOptions(options);
        this.subscribeEvents();//订阅options中的事件
        this.init();
    }

    Table.DEFAULTS = {
        selectAll: true,//是否显示全选按钮
        pagination: true,//是否显示分页栏
        pageSize: 10,//默认分页大小
        url: '',//数据请求的url
        autoload: true,//是否自动加载
        resouceType: '',//资源类型，用于权限控制
        autoAddFirstColumn: true,//自动加上第一列，全选的时候加复选框否则加序号列
        sortFields: '',//字段排序规则
        maxPageSize: 10000,
        dragMin: 10,//每次拖动最小宽度
        dragMax: 200,//每次拖动最大宽度
        keyField: 'key',//主键字段名称
        treeField: '',
        treeStatic: true,
        treeLevel: 2,
        onRowClick: undefined,//行点击回调
        onReady: undefined//数据加载完毕的回调
    };

    Table.prototype = $.extend({}, util.resourceControl.prototype);
    Table.prototype.constructor = Table;

    // override eventControl methods
    Table.prototype.getElement = function () {
        return this.$table;
    };

    Table.prototype.getNamespace = function () {
        return ns;
    };

    Table.prototype.getDefaults = function () {
        return Table.DEFAULTS;
    };

    Table.prototype.getOptions = function (options) {
        var opts = $.extend({}, this.getDefaults(), this.$table.data(), options);
        return opts;
    };

    Table.prototype.getTreeOptions = function () {
        var opts = this.options;
        return {
            treeField: opts.treeField,
            treeStatic: opts.treeStatic,
            treeLevel: opts.treeLevel
        }
    };

    function initParams(that) {
        var opts = that.options;
        //分页相关参数
        that.pageParams = {
            pageIndex: 1,
            pageSize: getOptsPageSize(opts),
            pageTotal: undefined
        };
        //排序相关参数
        that.sortParams = {};
        that.sortFieldArray = [];

        //用来组装查询条件
        that.filter = {};
    }

    function initSortFields(that) {
        var opts = that.options;
        var fields = opts.sortFields.split(';');
        var field, length = fields.length;
        if (length == 0) return;

        var sortFieldArray = that.sortFieldArray;

        for (var i = 0; i < length; i++) {
            field = fields[i].split(',');
            if (field.length != 3) continue;

            sortFieldArray.push({
                name: field[0],
                order: field[1],
                value: field[2] != 'asc' && field[2] != 'desc' ? '' : field[2]
            });
        }
        updateSortFields(that);
    }

    Table.prototype.init = function () {
        var $table = this.$table;
        var opts = this.options;
        var that = this;

        initParams(that);//分页，排序，查询参数
        if (opts.sortFields) {
            initSortFields(that);
        }
        createMask(that);//遮罩
        createWrapper(that, $table);//外层元素

        // no pagination
        if (!opts.pagination) {
            this.$innerWrapper.addClass('fullfill');
        }

        this.bindHandler($table);
        this.tableDrag = new TableDrag($table, this.$scrollContainer, this.$fixedContainer, {
            dragMax: opts.dragMax,
            dragMin: opts.dragMin
        });

        this.addToPermission(opts.resourceType);

        opts.autoload && _query(this);
    };

    // override resourceControl methods
    Table.prototype.addToPermission = function (resourceType) {
        if (typeof(resourceType) !== 'string') return;
        var that = this;
        var opts = this.options;

        if (opts.selectAll) {
            throw new Error('多选列表不支持配置成权限资源！');
        }

        this.off('rowClick.resouceChanged');
        this.on('rowClick.resouceChanged', function (e) {
            that.trigger('resouceChanged');
        });

        util.permission.add({
            resourceType: resourceType,
            controlType: 'table',
            control: this
        });
    };

    Table.prototype.getResourceId = function () {
        var ids = this.getIds();
        if (ids.length) {
            return ids[0];
        }
    };

    Table.prototype.bindHandler = function ($table) {
        var opts = this.options;
        $table.on('change.pagesize.' + ns, 'select[name=dataTable-pagesize]', $.proxy(changePageSize, this));
        $table.on('click.pagination.' + ns, '.dataTable-pagination a', $.proxy(clickPageItem, this));
        $table.on('click.rowClick.' + ns, '.dataTable tbody>tr', $.proxy(clickRow, this));

        if (opts.selectAll) {
            $table.on('click.selectAll.' + ns, '.dataTable-chk-all', $.proxy(selectAll, this));


            //bug 当多个table同时存在的时候，以下监听器可能会执行多次
            $document.one('keydown.ctrl.' + ns, $.proxy(pressCtrlDown, this));
            $document.on('keyup.ctrl.' + ns, $.proxy(pressCtrlUp, this));
        }

        var that = this;
        if (opts.sortFields) {
            $table.on('click.sort.' + ns, 'th.sort', $.proxy(sortChange, this));
            $table.on('mousedown.sort.' + ns, 'th.sort', function () {
                that.$table.removeClass('drag');
            });

            //bug 当多个table同时存在的时候，以下监听器可能会执行多次
            $document.one('keydown.shift.' + ns, $.proxy(pressShiftDown, this));
            $document.on('keyup.shift.' + ns, $.proxy(pressShiftUp, this));
        }

        var resizeTimer;
        var that = this;
        $(_).on('resize.' + ns, function () {
            resizeTimer && clearTimeout(resizeTimer);

            resizeTimer = setTimeout(function () {
                adjustTheadPosition(that, that.$scrollContainer);
            }, 60);
        });
    };

    Table.prototype.refresh = function (key) {
        this.refreshKey = key;
        this.refreshStatus = true;
        _query(this);
    };

    Table.prototype.query = function (params, append) {
        resetPageParams(this);
        _query(this, params, append);
    };

    Table.prototype.setUrl = function (url) {
        if(url) this.options.url = url;
    };

    Table.prototype.getParams = function () {
        var oldFilter = this.filter;
        var pageParams = this.pageParams;
        var sortParams = this.sortParams;

        return $.extend({
            pageIndex: pageParams.pageIndex,
            pageSize: pageParams.pageSize
        }, sortParams, oldFilter);
    };

    Table.prototype.addRowClick = function (selector, callback, trigger) {
        var that = this;
        var opts = this.options;
        this.$table.on('click.rowOperate', '.dataTable tbody>tr ' + selector, function (e) {
            e.stopPropagation();
            e.preventDefault();

            var $this = $(this);
            var $tr = $this.closest('tr');
            setRowSelected(that, $tr, trigger);

            if ($this.hasClass('disabled')) {
                return;
            }

            if (!(opts.selectAll && that.ctrlKeyPressing)) {
                callback($tr,e);
            }
        });
    };

    Table.prototype.setSelectedRows = function (vals, trigger) {
        if (!vals) return;
        if (vals.constructor != Array) return;

        var opts = this.options;
        var l = vals.length;
        var $rows = this.$rows;
        var $tr, removed, c = 0;
        for (var i = 0; i < l; i++) {
            $tr = $rows.filter('[data-' + opts.keyField + '="' + vals[i] + '"]');
            if ($tr.length) {
                if (!removed) {
                    $rows.filter('.selected').removeClass('selected');
                    removed = true;
                }
                c++;
                $tr.addClass('selected');
            }
        }

        if (c == 1 && (trigger !== false)) {
            this.trigger('rowClick', $tr);
        }
    };

    Table.prototype.getSelectedRows = function () {
        return this.$rows.filter('.selected');
    };

    Table.prototype.getFields = function (fieldName) {
        if (!fieldName) return [];
        if (!this.$rows) return [];

        var $rows = this.$rows.filter('.selected');
        var aryFields = [];
        $rows.each(function () {
            var value = $(this).data(fieldName);
            value && aryFields.push(value);
        });
        return aryFields;
    };

    Table.prototype.getRowByIndex = function (index) {
        index = parseInt(index);
        if (isNaN(index)) return [];
        if (!this.$rows) return [];

        return this.$rows.eq(index);
    };

    Table.prototype.getRowCount = function () {
        return this.$rows.length;
    };

    Table.prototype.removeAll = function () {
        this.$rows.remove();
        this.$rows = this.$scrollContainer.find('tbody>tr');
        this.trigger('allRemove');
        !this.$rows.filter('.selected').length && selectDefaultRow(this);
    };

    Table.prototype.removeRowByindex = function (index) {
        index = parseInt(index);
        this.$rows.eq(index).remove();
        this.$rows = this.$scrollContainer.find('tbody>tr');
        createOrderNo(this);
        this.trigger('rowRemove');
        !this.$rows.filter('.selected').length && selectDefaultRow(this);
    };

    Table.prototype.removeRow = function ($row) {
        if($row) {
            $row.remove();
            this.$rows = this.$scrollContainer.find('tbody>tr');
            createOrderNo(this);
            this.trigger('rowRemove');
            !this.$rows.filter('.selected').length && selectDefaultRow(this);
        }
    };

    //data 二维数据
    Table.prototype.addRows = function (data) {
        if(typeof(data) != 'object') return;
        if(!$.isArray(data)) data = [data];

        var l = data.length,
            n,
            row,
            col,
            html = [],
            attrs,
            tds,
            isKey = false, isAttr = false, isHtml = false, value = '',
            hasKey,
            autoAddFirstColumn = this.options.autoAddFirstColumn;

        /**
         * [
         * name: {key: true, attr: true, value: '',html: true,order: 1
         *
         * ...
         * ]
         *
         */



        for(var i = 0; i < l; i++) {
            row = data[i];

            html.push('<tr ');
            attrs = [];
            tds = [];
            n = row.length;
            hasKey = false;
            for(var j = 0; j < n; j++) {
                col = row[j];

                isKey = col['key'] === true;
                isAttr = col['attr'] === true;
                isHtml = col['html'] === true;
                value = util.isEmpty(col['value']) ? '' : col['value'];

                isKey && attrs.push('data-key=' + value);
                !isKey && isAttr && attrs.push('data-' + col['name'] + '=' + value);
                !isKey && !isAttr && tds.push('<td>' + value + '</td>');

                if(!hasKey && isKey) {
                    hasKey = true;
                }
            }

            !hasKey && attrs.push('data-key=""');

            html.push(attrs.join(' ') + '>');
            autoAddFirstColumn && html.push('<td class="center"><span class="dataTable-orderNo"></span></td>')
            html.push(tds.join(''));
            if(tds.length < this.colCount) {
                for(var l = this.colCount - tds.length; l > 0; l --) {
                    html.push('<td>&nbsp;</td>');
                }
            }
            html.push('</tr>');
        }
        this.$scrollContainer.find('tbody').append(html.join(''));
        this.$rows = this.$scrollContainer.find('tbody>tr');
        createOrderNo(this);
        this.trigger('rowAdd');

        !this.$rows.filter('.selected').length && selectDefaultRow(this);
    };

    Table.prototype.getFieldByIndex = function (index, fieldName) {
        if (!fieldName) return;

        var $row = this.getRowByIndex(index);

        if ($row.length) {
            return $row.data(fieldName);
        }
    };

    Table.prototype.getIds = function () {
        return this.getFields(this.options.keyField);
    };

    Table.prototype.resize = function () {
        adjustTheadPosition(this, this.$scrollContainer);
    };

    Table.prototype.subscribeEvents = function () {
        this.off('rowClick');
        this.off('ready');

        var opts = this.options;
        if (typeof(opts.onRowClick) === 'function') {
            this.on('rowClick', opts.onRowClick);
        }
        if (typeof(opts.onReady) === 'function') {
            this.on('ready', opts.onReady);
        }
    };

    function createWrapper(that, $table) {
        var $wrapper = $([
            '<div class="dataTable-wrapper">',
            '   <div class="dataTable-inner-wrapper">',
            '       <div class="dataTable-head"></div>',
            '       <div class="dataTable-body"></div>',
            '   </div>',
            '</div>'].join(''));

        $wrapper.appendTo($table);
        that.$wrapper = $wrapper;
        that.$innerWrapper = $table.find('.dataTable-inner-wrapper');
        that.$scrollContainer = $table.find('.dataTable-body');
        that.$fixedContainer = $table.find('.dataTable-head');
    }

    function createMask(that) {
        var $mask = $('<div class="dataTable-mask"></div>');
        $mask.appendTo(that.$table);
        that.$mask = $mask;
    }

    function _query(that, params, append) {
        var opts = that.options;
        var url = opts.url;
        var $mask = that.$mask;

        if (!url) return;

        if (!that.refreshStatus) {
            that.refreshKey = undefined;
            that.lastSelectedKey = undefined;
        }

        $mask.show();
        updateFilter(that, params, append);
        __query();

        function __query() {
            that.resetAjaxLoadStatus();
            util.ajax().html(url, that.getParams()).success(function (html) {
                that.setAjaxLoadStatus();
                resolveResponse(html, that);
                $mask.hide();
            }).error(function () {
                util.info('数据加载失败！');
                $mask.hide();
            });
        }
    }

    //解析返回的html
    function resolveResponse(html, that) {
        var $html = $(html);
        var opts = that.options;

        var $wrapper = that.$wrapper;
        var $scrollContainer = that.$scrollContainer;
        var $fixedContainer = that.$fixedContainer;

        that.treeTable && that.treeTable.destroy();
        that.treeTable = null;

        if (opts.pagination) {
            $wrapper.find('.dataTable-foot').remove();
            $wrapper.append($html.filter('.dataTable-foot'));
        }

        var $tableFixed = $html.filter('.dataTable');
        var $tableScroll = $tableFixed.clone();
        $wrapper.find('.dataTable').remove();

        $tableScroll.find('thead>tr').addClass('tr-hide');
        $scrollContainer.append($tableScroll);

        $fixedContainer.css('padding-right', measureScrollbar() + 'px');
        $tableFixed.find('tbody').remove();
        $fixedContainer.append($tableFixed);

        that.$tableFixed = $tableFixed;

        var $rows = $scrollContainer.find('tbody>tr');
        that.$rows = $rows;
        that.colCount = $fixedContainer.find('thead th').length;

        setThSortStyle(that);

        updatePageParams(that);
        if (opts.autoAddFirstColumn) {
            addFirstColumn(that);
        }

        bindSrcollHandler(that);

        that.tableDrag.createDraggers();

        if (!that.treeTable && opts.treeField) {
            that.treeTable = new TableTree(that.$table, that.getTreeOptions());
        }

        selectDefaultRow(that);
        that.trigger('ready', that);
    }

    function addFirstColumn(that) {
        var opts = that.options;
        var $fixedContainer = that.$fixedContainer;
        var $scrollContainer = that.$scrollContainer;
        var $rows = that.$rows;

        if (opts.selectAll) {
            $fixedContainer.find('thead>tr').prepend([
                '<th style="width: 50px;" title="全选">',
                '   <div class="checkbox">',
                '       <label><input type="checkbox" class="dataTable-chk-all"></label>',
                '   </div>',
                '</th>'].join(''));
            $scrollContainer.find('thead>tr').prepend('<th style="width: 50px;">&nbsp;</th>');
        } else {
            $fixedContainer.find('thead>tr').prepend('<th style="width: 50px;">序号</div></th>');
            $scrollContainer.find('thead>tr').prepend('<th style="width: 50px;">&nbsp;</th>');
        }
        $rows.prepend('<td class="center"' + (opts.selectAll ? ' title="按ctrl再点击行，可以进行多选"' : '') + '><span class="dataTable-orderNo"></span></td>');

        createOrderNo(that);
    }

    function createOrderNo(that) {
        var listRange = getStartEnd(that);
        that.$table.find('.dataTable-orderNo').each(function (i) {
            $(this).text(listRange.start + i);
        });
    }

    function getStartEnd(that) {
        var opts = that.options;
        var $table = that.$table;
        if (opts.pagination) {
            var $listRange = $table.find('.dataTable-range');
            var listRange = util.trim($listRange.text()).replace(/[\[\]]/g, "").split('-');
            return {
                start: parseInt(listRange[0]),
                end: parseInt(listRange[1])
            }
        } else {
            return {
                start: 1,
                end: that.$rows.length
            }
        }
    }

    function setThSortStyle(that) {
        var $table = that.$table;
        var sortFieldArray = that.sortFieldArray;
        var $ths = $table.find('.dataTable-head th[data-field]');
        var $th, l = sortFieldArray.length, item;
        for (var i = 0; i < l; i++) {
            item = sortFieldArray[i];
            $th = $ths.filter('[data-field="' + item.name + '"]');
            $th.addClass('sort');

            if (!item.value) continue;
            if (item.value == 'desc') {
                $th.addClass('desc');
            } else {
                $th.addClass('asc');
            }
        }
    }


    function bindSrcollHandler(that) {
        var $scrollContainer = that.$scrollContainer;
        $scrollContainer.on('scroll.' + ns, function () {
            adjustTheadPosition(that, $(this));
        });
    }

    function adjustTheadPosition(that, $scrollContainer) {
        that.$tableFixed && that.$tableFixed.css('left', '-' + $scrollContainer.scrollLeft() + 'px');
    }

    function changePageSize(e) {
        var $pageSize = $(e.currentTarget);
        var pageParams = this.pageParams;
        pageParams.pageIndex = 1;
        pageParams.pageSize = parseInt($pageSize.val());
        _query(this);
    }

    function clickPageItem(e) {
        var $page = $(e.currentTarget);
        var $li = $page.parent();
        if ($li.hasClass('disabled')) {
            e.preventDefault();
            return false;
        }
        parsePagination(this, $page);
        _query(this);
    }

    //根据点击的分页项得到要跳转的pageIndex
    function parsePagination(that, $page) {
        var pageParams = that.pageParams;

        var pageText = $page.text();
        var pageIndex = pageParams.pageIndex;
        var pageTotal = pageParams.pageTotal;
        switch (pageText) {
            case "首页":
                pageIndex = 1;
                break;
            case "末页":
                pageIndex = pageTotal;
                break;
            case "上页":
                pageIndex--;
                if (pageIndex < 1) pageIndex = 1;
                break;
            case "下页":
                pageIndex++;
                if (pageIndex > pageTotal) pageIndex = pageTotal;
                break;
            default:
                pageIndex = parseInt(pageText);
        }
        pageParams.pageIndex = pageIndex;
    }

    //params为外部参数，append表示新参数是覆盖的方式(true)，还是替换的方式(true)
    function updateFilter(that, params, append) {
        var oldFilter = that.filter;
        var filter;
        if (params) {
            if (append === false) {
                filter = params;
            } else {
                filter = $.extend({}, oldFilter, params);
            }
            that.filter = filter;
        }
    }

    function selectDefaultRow(that) {
        var $rows = that.$rows;
        var opts = that.options;
        if ($rows.length) {
            var keyField = opts.keyField;
            var valid = false;

            //刷新时根据传入的key或者最后一次点击的行刷新
            if (that.refreshStatus) {
                if (that.refreshKey) {
                    var $tr = $rows.filter('[data-' + keyField + '="' + that.refreshKey + '"]');
                    if ($tr.length) {
                        $tr.eq(0).trigger('click');
                        valid = true;
                    }
                } else {
                    var $tr = $rows.filter('[data-' + keyField + '="' + that.lastSelectedKey + '"]');
                    if ($tr.length) {
                        $tr.eq(0).trigger('click');
                        valid = true;
                    }
                }
                that.refreshStatus = undefined;
            }

            if (!valid) {
                $rows.eq(0).trigger('click');
            }
        }
    }

    function selectAll(e) {
        var checkbox = e.currentTarget;
        var $rows = this.$rows;
        if (checkbox.checked) {
            $rows.addClass('selected');
        } else {
            $rows.removeClass('selected');
            if ($rows.length) {
                var $tr = $rows.eq(0);
                $tr.addClass('selected');
                this.trigger('rowClick', $tr);
            }
        }
    }

    function pressCtrlDown(e) {
        if (e.which == 17) {
            this.ctrlKeyPressing = true;
        }
    }

    function pressCtrlUp(e) {
        this.ctrlKeyPressing = false;
        $document.one('keydown.ctrl.' + ns, $.proxy(pressCtrlDown, this));
    }

    function clickRow(e) {
        var $tr = $(e.currentTarget);
        setRowSelected(this, $tr, $tr.data('trigger'));
    }

    function setRowSelected(that, $tr, trigger) {
        var opts = that.options;
        var selectAll = false;
        if (opts.selectAll) {
            var isSelected = $tr.hasClass('selected');
            if (that.ctrlKeyPressing) {

                var $rows = that.$rows.filter('.selected');
                if (!(isSelected && $rows.length === 1)) {
                    $tr.toggleClass('selected', !isSelected);
                }
                selectAll = true;
            }
        }

        if (!selectAll) {
            var $rows = that.$rows.filter('.selected');
            $rows.removeClass('selected');
            $tr.addClass('selected');
            trigger !== false && that.trigger('rowClick', $tr);
            that.lastSelectedKey = $tr.data(opts.keyField);
        }
    }

    function resetPageParams(that) {
        var pageParams = that.pageParams;
        pageParams.pageIndex = 1;
        pageParams.pageSize = getOptsPageSize(that.options);
    }

    function getOptsPageSize(opts) {
        return opts.pagination ?
            (opts.pageSize || Table.DEFAULTS.pageSize) :
            (opts.maxPageSize || Table.DEFAULTS.maxPageSize);
    }

    function updatePageParams(that) {
        var opts = that.options;

        if (opts.pagination) {
            var $pagination = that.$table.find('.dataTable-pagination');
            var pageParams = that.pageParams;
            pageParams.pageIndex = parseInt($pagination.data('pageindex'));
            pageParams.pageTotal = parseInt($pagination.data('totalpages'));
            pageParams.pageSize = parseInt($pagination.data('pagesize'));
        }
    }

    function measureScrollbar() { // thx walsh
        var scrollDiv = document.createElement('div');
        scrollDiv.className = 'modal-scrollbar-measure';
        $body.append(scrollDiv);
        var scrollbarWidth = scrollDiv.offsetWidth - scrollDiv.clientWidth;
        $body[0].removeChild(scrollDiv);
        return scrollbarWidth;
    }

    function pressShiftDown(e) {
        if (e.which == 16) {
            this.shiftKeyPressing = true;
        }
        preventSelectStart(ns);
    }

    function pressShiftUp(e) {
        if (this.shiftKeyPressing) {
            if (this.shiftClicked) {
                updateSortFields(this);
                _query(this);
                this.shiftClicked = false;
            }
            this.shiftKeyPressing = false;
        }
        restoreSelectStart(ns);
        $document.one('keydown.shift.' + ns, $.proxy(pressShiftDown, this));
    }

    function updateSortFields(that) {
        var sortFieldArray = that.sortFieldArray;
        sortFieldArray.sort(function (a, b) {
            return a.order - b.order;
        });

        $.each(sortFieldArray, function (i, e) {
            e.order = i;
        });

        var a = sortFieldArray.filter(function (e) {
            return !!e.value;
        });

        a = $.map(a, function (e) {
            var o = {};
            o[e.name] = e.value;
            return o;
        });

        if (a.length > 0) {
            that.sortParams = {sortFields: JSON.stringify(a)};
        } else {
            that.sortParams = {};
        }
    }

    function sortChange(e) {
        if (this.$table.hasClass('drag')) return;

        var that = this, $th = $(e.currentTarget);
        var sortFieldArray = that.sortFieldArray, shiftKeyPressing = that.shiftKeyPressing;

        if (!shiftKeyPressing) {
            $th.siblings('.sort').removeClass('asc').removeClass('desc');
        } else if (!that.shiftClicked) {
            that.shiftClicked = true;
        }

        var flag = 0;
        if (!$th.hasClass('desc') && !$th.hasClass('asc')) {
            $th.addClass('asc');
            flag = 1;
        } else if ($th.hasClass('desc') && shiftKeyPressing) {
            $th.removeClass('desc');
            flag = 2;
        } else if ($th.hasClass('desc') && !shiftKeyPressing) {
            $th.removeClass('desc').addClass('asc');
            flag = 1;
        } else {
            $th.removeClass('asc').addClass('desc');
            flag = 3;
        }

        var field = $th.data('field');
        if (!field) return;

        var length = sortFieldArray.length;
        $.each(sortFieldArray, function (i, e) {
            if (shiftKeyPressing) {
                if (e.name == field) {
                    e.value = flag == 1 ? 'asc' : flag == 2 ? '' : 'desc';
                    if (e.order == i) {
                        //更新order 取order 与 索引不一致的最后一个元素的order 加一
                        e.order = getOrder();
                    }
                }
            } else {
                if (e.name != field) {
                    e.value = '';
                } else {
                    e.value = flag == 1 ? 'asc' : flag == 2 ? '' : 'desc';
                    e.order = 0 - length;
                }
            }
        });

        if (!shiftKeyPressing) {
            updateSortFields(this);
            _query(this);
        }

        function getOrder() {
            var index = 0 - length;
            $.each(sortFieldArray, function (i, e) {
                if (i != e.order && e.order >= index) {
                    index = e.order + 1;
                }
            });
            return index;
        }
    }

    var _table = function (selector, option) {
        var obj = [];

        $(selector).each(function () {
            var that = $(this);
            var data = that.data('util.table');
            var options = typeof option == 'object' && option;

            var id = util.generateId(that, 'dataTable-');
            if (!data) {
                data = new Table(this, options);
                that.data('util.table', data);

                objects[id] = data;
            }
            obj.push(data);
        });

        if (obj.length == 1) return obj[0];
        return obj;
    };

    $.extend(_table, {
        getTables: function () {
            return objects;
        },
        get: function (id) {
            if (!id) {
                for (var i in objects) {
                    if (objects.hasOwnProperty(i)) {
                        return objects[i];
                    }
                }
            }
            return objects[id];
        },
        remove: function (id) {
            delete objects[id];
        },
        each: function (callback) {
            var t;
            for (var i in objects) {
                if (!objects.hasOwnProperty(i)) {
                    continue;
                }
                t = objects[i];
                callback(t);
            }
        }
    });

    _.util = $.extend(util, {
        table: _table
    });


}(window, jQuery);
/**
 * author zengxf
 * added 2015-09-09
 */

+function (_, $) {


    var ns = 'util.uploader',
        util = _.util || {},
        _uploader,
        uploadIcon = {
            file: '<i class="fa fa-upload"></i>',
            video: '<i class="fa fa-video-camera"></i>',
            audio: '<i class="fa fa-music"></i>',
            image: '<i class="fa fa-image"></i>'
        },
        map = {},
        curId;

    (function(){
        typeof(WebUploader) != 'undefined' && WebUploader.Uploader.register({
            "before-send-file": "beforeSendFile",
            "before-send": "beforeSend",
            "after-send-file": "afterSendFile"
        }, {
            beforeSendFile: function (file) {
                _beforeSendFile(file, this, map[curId]);
            },
            beforeSend: function (block) {
                return _beforeSend(block, this, map[curId]);
            },
            afterSendFile: function (file) {
                return _afterSendFile(file, this, map[curId]);
            }
        });
    })();

    function Uploader(element, options) {
        this.$element = $(element);
        this.options = this.getOptions(options);
        this.uploader = null;
        this.id = new Date().getTime() + '' + Math.random();
        this.render();
        this.init();
        map[this.id] = this;
    }

    Uploader.DEFAULTS = {
        uploadType: 'file',
        resize: false,
        compress: false,
        chunked: true,
        server: '',
        md5CheckUrl: '',
        chunkCheckUrl: '',
        chunkMergeUrl: ''
    };

    //2.10 => 2.1
    function replaceZero(v) {
        v = v.replace(/0+$/, '');
        if (v.lastIndexOf('.') == v.length - 1) {
            v = v.substring(0, v.length - 1);
        }
        return v;
    }

    //单位换算
    function getSize(size) {
        return '' +
        size < 1024 ? size + 'B' :
            size < 1048576 ? replaceZero((size / 1024.0).toFixed(2)) + 'KB' :
                size < 1073741824 ? replaceZero((size / 1048576.0).toFixed(2)) + 'MB' :
                    size < 1099511627776 ? replaceZero((size / 1073741824.0).toFixed(2)) + 'GB' : size + 'B';
    }

    function retryOrCancel(e) {
        var $action = $(e.currentTarget),
            action = $action.hasClass('retry') ? 'retry' : 'removeFile',
            fileId = $action.data('fileId'),
            file = this.uploader.getFile(fileId),
            $item = $action.closest('.item');

        this.uploader[action](file);

        if (action == 'removeFile') {
            $action.data('from') !== 'success' && this.count--;//未上传文件减一
            delete this.files[file.id];
            $item.remove();
        } else {
            $item.removeClass('fail');
        }
    }

    function uploadSuccess(file) {
        this.count--;//未上传文件减一

        var o = this.files[file.id];
        o.state = 2; //上传成功
        o.url = file.url || '';

        $('#' + file.id).removeClass('fail').addClass('success').find('p.state').html('上传成功！<a href="javascript:;" data-from="success"  data-file-id="' + file.id + '" class="removeFile action">删除</a>');
    }

    function uploadError(file) {
        this.files[file.id].state = 1; //上传失败
        $('#' + file.id).removeClass('success').addClass('fail').find('p.state').html('上传出错！<a href="javascript:;" data-file-id="' + file.id + '" class="retry action">重试</a><a href="javascript:;"  data-file-id="' + file.id + '" class="removeFile action">取消</a>');
    }

    function uploadComplete(file) {
        setTimeout(function () {
            $('#' + file.id).find('.progress').fadeOut();
        }, 800);
    }

    function uploadBeforeSend(object, data) {
        var file = object.file;
        file && (data.uniqueFileName = this.files[file.id].uniqueFileName);
    }

    function uploadAccept(object, res) {
        var o = this.files[object.file.id];
        if (res && res.code == 200 && !o.chunk) {
            object.file.url = (res.data && res.data.path) || '';
        }
    }

    function _beforeSendFile(file, me, that) {
        //生成文件名，每个文件上传前调用一次
        var fileMd5Seed = '' + file.name + file.type + file.lastModifiedDate + file.size + (new Date().getTime()) + Math.random(),
            uniqueFileName = md5(fileMd5Seed),
            chunkSize = me.options.chunkSize,
            ofile = that.files[file.id];

        ofile.uniqueFileName = uniqueFileName;
        ofile.chunk = Math.ceil(file.size / chunkSize) > 1;//是否需要分片
    }

    function _beforeSend(block, me, that) {
        var server = me.options.chunkCheckUrl,
            task = new $.Deferred(),
            uniqueFileName = that.files[block.file.id].uniqueFileName;

        $.ajax({
            type: "POST",
            url: server,
            data: {
                status: "chunkCheck",
                name: uniqueFileName,
                chunkIndex: block.chunk,
                size: block.end - block.start
            },
            cache: false,
            dataType: "json"
        }).then(function (res) {
            if (res.code == 200 && res.data.state) {
                task.reject();
            } else {
                task.resolve();
            }
        }, function () {
            task.resolve();
        });
        return $.when(task);
    }

    function _afterSendFile(file, me, that) {
        var server = me.options.chunkMergeUrl,
            chunkSize = me.options.chunkSize,
            chunksTotal = 0;

        if ((chunksTotal = Math.ceil(file.size / chunkSize)) > 1) {
            var task = new $.Deferred(),
                uniqueFileName = that.files[file.id].uniqueFileName;

            $.ajax({
                type: "POST",
                url: server,
                data: {
                    status: "chunksMerge",
                    name: uniqueFileName,
                    chunks: chunksTotal,
                    ext: file.ext,
                    md5: ''
                },
                cache: false,
                dataType: "json"
            }).then(function (res) {
                if (res.code == 200 && res.data.state) {
                    task.resolve();
                    file.url = res.data.path;
                } else {
                    task.reject();
                }
            }, function () {
                task.reject();
            });
            return $.when(task);
        }
    }

    Uploader.prototype = $.extend({}, util.eventControl.prototype, {
        getNamespace: function () {
            return ns;
        },
        getElement: function () {
            return this.$element;
        },
        getDefaults: function () {
            return Uploader.DEFAULTS;
        },
        getOptions: function (options) {
            var defaults = this.getDefaults(),
                opts = $.extend({}, defaults, options || {}, this.$element.data());
            return opts;
        },
        render: function () {
            var opts = this.options,
                $element = this.$element,
                h = ['<div>',
                    '                <div class="picker">选择文件</div>',
                    '                <button class="btn btn-primary btn-start"  type="button">',
                    uploadIcon[opts.uploadType],
                    ' 开始上传</button>',
                    '            </div>',
                    '            <div class="uploader-list"></div>'].join("");

            $element.append(h);
        },
        getUnuploadCount: function () {
            return this.count;
        },
        getValue: function () {
            var os = this.files, o, a = [];
            for (var i in os) {
                if (!os.hasOwnProperty(i)) continue;
                o = os[i];
                o.url && a.push(o.url);
            }
            return a.join(',');
        },
        reset: function () {
            this.count = 0;
            this.files = {};
            this.uploader.reset();
            this.$element.find('.item').remove();
        },
        refresh: function () {
            this.uploader.refresh()
        },
        init: function () {
            var opts = this.options,
                $element = this.$element,
                $list = $element.find('.uploader-list'),
                state = 'pending',
                $btn = $element.find('.btn-start'),
                that = this;

            that.count = 0;
            that.files = {};

            $element.on('click.' + ns, '.action', $.proxy(retryOrCancel, this));

            var uploader = this.uploader = WebUploader.create($.extend(opts, {
                pick: $element.find('.picker')[0]
            }));

            uploader.on('fileQueued', function (file) {
                curId = that.id;
                that.count++;
                var o = that.files[file.id] = {};
                o.state = 0;//未上传

                $list.append('<div id="' + file.id + '" class="item">' +
                    '<h4 class="info">' + file.name + '，大小：' + getSize(file.size) + '<p class="state">等待上传...<a href="javascript:;"  data-file-id="' + file.id + '" class="removeFile action">取消</a></p></h4>' +
                    '</div>');

                that.trigger('fileQueued', file);
            });

            // 实时显示进度条
            uploader.on('uploadProgress', function (file, percentage) {
                var $li = $('#' + file.id),
                    $percent = $li.find('.progress .progress-bar');

                if (!$percent.length) {
                    $percent = $('<div class="progress progress-striped active">' +
                        '<div class="progress-bar" role="progressbar" style="width: 0%">' +
                        '</div>' +
                        '</div>').appendTo($li).find('.progress-bar');
                }

                $li.find('p.state').text('上传中...');
                $percent.parent().show();
                $percent.css('width', percentage * 100 + '%');
            });

            $btn.on('click', function () {
                if (state === 'uploading') {
                    uploader.stop();
                } else if (that.count > 0) {//如果有未上传成功的文件
                    uploader.upload();
                }
            });

            uploader.on('all', function (type) {
                if (type === 'startUpload') {
                    state = 'uploading';
                } else if (type === 'stopUpload') {
                    state = 'paused';
                } else if (type === 'uploadFinished') {
                    state = 'done';
                }

                if (state === 'uploading') {
                    $btn.html(uploadIcon[opts.uploadType] + ' 暂停上传');
                } else {
                    $btn.html(uploadIcon[opts.uploadType] + ' 开始上传');
                }
            });

            //文件或分片上传前调用
            uploader.on('uploadBeforeSend', $.proxy(uploadBeforeSend, this));

            //上传成功回调
            uploader.on('uploadSuccess', $.proxy(uploadSuccess, this));

            //上传失败回调
            uploader.on('uploadError', $.proxy(uploadError, this));

            //上传完成回调
            uploader.on('uploadComplete', $.proxy(uploadComplete, this));

            uploader.on('uploadAccept', $.proxy(uploadAccept, this));
        }
    });

    Uploader.prototype.constructor = Uploader;

    _uploader = function (selector, option) {
        var obj = [];

        $(selector).each(function () {
            var $this = $(this),
                data = $this.data(ns),
                options = typeof option == 'object' && option;

            if (!data) $this.data(ns, (data = new Uploader(this, options)));

            obj.push(data);
        });

        if (obj.length == 1) return obj[0];
        return obj;
    };

    _.util = $.extend(util, {
        uploader: _uploader
    });

}(window, jQuery);
/**
 * author zengxf
 * added 2015-05-16
 */
;
(function (_, $) {
    var util = _.util || {};

    _.util = $.extend(util, {
        url: (function () {
            var search = location.search + "",
                href = location.href.toString().replace(search, ''),
                isParentSupport = parent != _ && parent.util && parent.util.url,
                baseUrl = $('#global-url-base').attr('href'),
                $childFrame = $('.child-frame'),
                childFrame = document.getElementById('child-frame'),
                $body = $(document.body),
                staticJump = function (url, tableId) {
                    if (!url) {
                        if (!childFrame) return;

                        $body.removeClass('child-frame-shown');
                        $childFrame.addClass('hidden');
                        childFrame.src = 'about:blank';

                        if (tableId !== false && util.table) {
                            var tables = util.table.getTables();
                            if (tables) {
                                if (tableId && tableId in tables) {
                                    tables[tableId].refresh();
                                } else {
                                    for (var i in tables) {
                                        if (tables.hasOwnProperty(i)) {
                                            tables[i].refresh();
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        childFrame.src = url;
                        $body.addClass('child-frame-shown');
                        $childFrame.removeClass('hidden');
                    }
                },
                clearParam = function (name) {
                    var regExp = eval('/[&]?' + name + '=[^&]*/gi');
                    search = search.replace(regExp, '');
                },
                addParam = function (name, value) {
                    search = search + '&' + name + '=' + value;
                },
                clearParams = function (arr) {
                    for (var i in arr) {
                        clearParam(arr[i]);
                    }
                },
                addParams = function (queryString) {
                    var params = queryString.split('&');
                    var param;
                    for (var i in params) {
                        param = params[i].split('=');
                        addParam(param[0], param[1]);
                    }
                },
                _getUrl = function () {
                    return href + (search && ('?' + (search.indexOf('&') == 0 ? search.substring(1) : search)));
                },
                _addParams = function (queryString) {
                    //mode=1&key=abcd
                    addParams(queryString);
                    return this;
                },
                _clearParams = function (params) {
                    //1. 'mode','key'
                    //2. ['mode','key']
                    //3. 'mode,key'
                    var arr = arguments;
                    if (typeof(params) == 'object' && params.constructor == Array) {
                        arr = params;
                    } else if (typeof(params) == 'string') {
                        arr = params.split(',');
                    }
                    clearParams(arr);
                    return this;
                },
                _base = function () {
                    return baseUrl;
                },
                _reload = function (params, queryString) {
                    params && clearParams(params);
                    queryString && addParams(queryString);
                    location.href = _getUrl();
                },
                _go = function (url, current) {
                    if (!url) {
                        this.reload();
                    } else if (current || !childFrame) {
                        location.href = baseUrl + url;
                    } else {
                        staticJump(url);
                    }
                },
                _postUrl = function(resourceType) {
                    return resourceType && [
                        'data/edit/',
                        resourceType,
                        '/add'
                    ].join('');
                },
                _putUrl = function(resourceType) {
                    return resourceType && [
                        'data/edit/',
                        resourceType,
                        '/modify'
                    ].join('');
                },
                _goAdd = function (resoureType, routePath, queryString) {
                    var url = [
                        'data/query/',
                        resoureType,
                        '/get?routePath=',
                        routePath,
                        '&mode=1',
                        queryString ? '&' + queryString : ''
                    ].join('');

                    this.go(url);
                },
                _goEdit = function (resoureType, routePath, key, queryString) {
                    var url = [
                        'data/query/',
                        resoureType,
                        '/get?routePath=',
                        routePath,
                        '&mode=2&key=',
                        key,
                        queryString ? '&' + queryString : ''
                    ].join('');

                    this.go(url);
                },
                __back = function (tableId) {
                    staticJump('', tableId);
                },
                _back = function (backUrl, tableId) {
                    if (backUrl) {
                        if (isParentSupport) {
                            parent.util.url.go(backUrl, true);
                        } else {
                            this.go(backUrl, true);
                        }
                        return;
                    }

                    if (isParentSupport) {
                        parent.util.url._back(tableId);
                    }
                },
                _format = function (obj) {
                    return $.param(obj);
                },
                _anchor,
                _download = function(url) {
                    if(!url) return;
                    if(!_anchor) {
                        _anchor = $('<a href="" title="" target="child-frame">&nbsp;</a>');
                        _anchor.appendTo($(document.body));
                    }
                    _anchor[0].href = url;
                    _anchor[0].click();
                };

            if (search != "") search = search.substring(1);

            return {
                addParams: _addParams,
                clearParams: _clearParams,
                base: _base,
                get: _getUrl,
                reload: _reload,
                go: _go,
                goAdd: _goAdd,
                goEdit: _goEdit,
                _back: __back,
                back: _back,
                postUrl: _postUrl,
                putUrl: _putUrl,
                format: _format,
                download: _download
            }
        })()
    })
})(window, jQuery);

(function ($) {

    if(typeof(FormValidation) === 'undefined') return;

    var uploadMap = {
        file: {
            text: '文件',
            unit: '个'
        },
        video: {
            text: '视频',
            unit: '个'
        },
        audio: {
            text: '音频',
            unit: '个'
        },
        image: {
            text: '图片',
            unit: '张'
        }
    }, getMessage = function (l, uploadType) {
        return '请上传' + l + uploadMap[uploadType].unit + uploadMap[uploadType].text + '！';
    };

    FormValidation.Validator.upload = {
        validate: function (validator, $field, options) {
            var value = $field.val();
            if (value === '') {
                return true;
            }

            var maxSize = $field.data('fvUploadMaxSize'),
                fixSize = $field.data('fvUploadFixedSize'),
                jsonValue = $field.data('jsonValue'),
                uploadType = $field.data('uploadType'),
                l = jsonValue ? JSON.parse(value).length : value.split(',').length;

            if (maxSize) {
                if (l > maxSize) {
                    return {
                        valid: false,
                        message: '超出上传限制，请重新上传！'
                    }
                } else if (l < maxSize && fixSize) {
                    return {
                        valid: false,
                        message: getMessage(maxSize, uploadType)
                    }
                }
            }

            return true;
        }
    };
}(jQuery));

/**
 * @author 曾险峰
 * 2015-08-20
 */

+function (_, $) {


    var ns = 'mam.win';

    function Win(options) {
        this.init(options);
    }

    Win.DEFAULTS = {
        name: 'modal-win',
        title: '',
        width: 900,
        height: 600,
        distance: 50,
        onConfirm: function () {
        },
        onClose: function () {
        },
        onLoad: function () {
        }
    };

    Win.prototype.getDefaults = function () {
        return Win.DEFAULTS;
    };

    Win.prototype.getOptions = function (options) {
        var opts = $.extend({}, this.getDefaults(), options);
        return opts;
    };

    Win.prototype.getDelegateOptions = function (options) {
        var opts = $.extend({}, this.options, options);
        return opts;
    };

    Win.prototype.getName = function () {
        return this.options.name;
    };

    Win.prototype.getFrame = function() {
        return this.modalFrame;
    };

    Win.prototype.init = function (options) {
        var opts = this.getOptions(options);
        this.options = opts;
        if (!opts.name) {
            opts.name = Win.DEFAULTS.name;
        }

        this.$body = $(document.body);
        this.$modal = $(_getTemplate());

        var $modal = this.$modal;
        var $body = this.$body;
        $modal.appendTo($body);// add to dom

        this.modalFrame = $modal.find('iframe')[0];
        this.$title = $modal.find('.modal-title');
        this.$dialog = $modal.find('.modal-dialog');

        $modal.modal();

        this.bindEvents();
        this.subscribeEvents();

        this.reset();

        function _getTemplate() {
            return ['<div class="modal modal-modal fade" data-backdrop="false" data-show="false" data-keyboard="false" role="dialog">',
                '  <div class="modal-dialog">',
                '    <div class="modal-content">',
                '      <div class="modal-header">',
                '        <button type="button" class="close btn-close">&times;</button>',
                '        <h4 class="modal-title"></h4>',
                '      </div>',
                '      <div class="modal-body"><iframe name="',
                opts.name,
                '" class="modal-frame" src="about:blank"></iframe></div>',
                '      <div class="modal-footer">',
                '        <button type="button" class="btn btn-primary btn-confirm">确定</button>',
                '        <button type="button" class="btn btn-default btn-close">返回</button>',
                '      </div>',
                '    </div>',
                '  </div>',
                '</div>'].join("");
        }
    };

    Win.prototype.resetPosition = function () {
        var opts = this.options;
        this.setPosition(opts.width, opts.height);
    };

    Win.prototype.getWinSize = function () {
        var fullWindowWidth = window.innerWidth;
        if (!fullWindowWidth) { // workaround for missing window.innerWidth in IE8
            var documentElementRect = document.documentElement.getBoundingClientRect();
            fullWindowWidth = documentElementRect.right - Math.abs(documentElementRect.left);
        }
        var fullWindowHeight = window.innerHeight;
        if (!fullWindowHeight) { // workaround for missing window.innerHeight in IE8
            var documentElementRect = document.documentElement.getBoundingClientRect();
            fullWindowHeight = documentElementRect.bottom - Math.abs(documentElementRect.top);
        }

        return {
            width: fullWindowWidth,
            height: fullWindowHeight
        }
    };

    Win.prototype.setPosition = function (width, height) {
        var winSize = this.getWinSize();
        var opts = this.options;
        if(width >= winSize.width) width = winSize.width - opts.distance;
        if(height >= winSize.height) height = winSize.height - opts.distance;

        this.$dialog.css({
            'width': width + 'px',
            'height': height + 'px',
            'margin-left': '-' + (width / 2) + 'px',
            'margin-top': '-' + (height / 2) + 'px'
        });
    };

    Win.prototype.reset = function () {
        this.resetPosition();
    };

    Win.prototype.bindEvents = function () {
        this.$modal.on('click.' + ns, '.btn-confirm', $.proxy(this.confirm, this));
        this.$modal.on('click.' + ns, '.btn-close', $.proxy(this.close, this));
    };

    Win.prototype.subscribeEvents = function () {
        this.off('confirm');
        this.off('close');
        this.off('load');

        var opts = this.options;
        if (typeof(opts.onConfirm) === 'function') {
            this.on('confirm', opts.onConfirm);
        }
        if (typeof(opts.onClose) === 'function') {
            this.on('close', opts.onClose);
        }
        if (typeof(opts.onLoad) === 'function') {
            this.on('load', opts.onLoad);
        }
    };

    Win.prototype.confirm = function (e) {
        if (!this.trigger('confirm',this))  return;
        this.hide();
    };

    Win.prototype.close = function (e) {
        if (!this.trigger('close',this))  return;
        this.hide();
    };

    Win.prototype.open = function (options) {
        var $modal = this.$modal;
        var that = this;
        var opts = this.getDelegateOptions(options);
        this.options = opts;

        this.subscribeEvents();

        opts.title && this.setTitle(opts.title);

        var src = opts.url;

        if (src) {
            var frame = this.modalFrame;
            $modal.one('shown.bs.modal', function () {
                frame.src = src;
                frame.onload = function () {
                    that.trigger('load',that);
                    that.loaded = true;
                };
            });
        }

        this.setPosition(opts.width, opts.height);

        util.mask.show();
        $modal.modal('show');
    };

    Win.prototype.hide = function () {
        var $modal = this.$modal;
        var that = this;

        var frame = this.modalFrame;
        frame.onload = null;
        frame.src = 'about:blank';

        this.loaded = false;
        this.options = this.getOptions();

        this.setTitle('');

        $modal.one('hidden.bs.modal', function () {
            util.mask.hide();
            that.reset();
        });

        $modal.modal('hide');
    };

    Win.prototype.setTitle = function (title) {
        this.$title.text(title);
    };

    Win.prototype.on = function (type, data, callback) {
        if (arguments.length == 2) {
            callback = data;
            data = undefined;
        }

        if (typeof(callback) != 'function') return;
        this.$modal.on(type + '.' + ns, data, callback);
    };

    Win.prototype.one = function (type, data, callback) {
        if (arguments.length == 2) {
            callback = data;
            data = undefined;
        }

        if (typeof(callback) != 'function') return;
        this.$modal.one(type + '.' + ns, data, callback);
    };

    Win.prototype.off = function (type) {
        type && this.$modal.off(type);
    };

    Win.prototype.trigger = function (type, data) {
        var e = $.Event(type + '.' + ns);
        this.$modal.trigger(e, data);
        return !e.isDefaultPrevented();
    };


    var util = _.util || {};

    var wins = {};

    var defautWin = Win.DEFAULTS.name;
    wins[defautWin] = new Win();

    var _open = function (options) {
        var name = options.name || defautWin;
        var win = wins[name];
        if(!win) {
            win = new Win(options);
            wins[name] = win;
        }
        win.open(options);
        return win;
    };

    var _hide = function (winName) {
        var name = winName || defautWin;
        var win = wins[name];
        if(win) {
            win.hide();
        }
        return win;
    };

    _.util = $.extend(util, {
        win: {
            open: _open,
            hide: _hide
        }
    });


}(window, jQuery);