(function () {
  function mountAlert(seed) {
    var type = seed.dataset.type || 'info';
    var icon = seed.dataset.icon || 'info-circle';
    var text = seed.dataset.text || '';

    var wrap = document.createElement('div');
    wrap.className = 'alert alert-' + type + ' alert-toast';
    wrap.setAttribute('role', 'alert');
    wrap.innerHTML =
      '<i class="fa fa-' + icon + '" style="margin-right:8px;"></i>' +
      text +
      '<button type="button" class="close" data-dismiss="alert" aria-label="Close">' +
        '<span aria-hidden="true">&times;</span>' +
      '</button>';

    seed.replaceWith(wrap);
    return wrap;
  }

  var alerts = Array.prototype.map.call(
    document.querySelectorAll('.alert-seed'),
    mountAlert
  );

  setTimeout(function () {
    alerts.forEach(function (el) {
      if (window.jQuery && typeof jQuery(el).alert === 'function') {
        jQuery(el).alert('close');
      } else {
        el.remove();
      }
    });
  }, 3000);
})();
