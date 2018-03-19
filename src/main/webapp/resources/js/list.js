$(document).ready(function () {
    $('#total').text('Total: Rs. 0.0');

    $('.submit-btn').mouseover(function () {
        if (!$(this).hasClass('disabled')) {
            $(this).addClass('hovered-background');
            $(this).removeClass('contrast-background');
        }
    }).mouseout(function () {
        if (!$(this).hasClass('disabled')) {
            $(this).addClass('contrast-background');
            $(this).removeClass('hovered-background');
        }
    });

    $('.category-checkbox:checkbox').each(function () {
        $(this).change(function () {
            var totalCost = 0.0;
            $('.category-checkbox:checkbox:checked').each(function () {
                totalCost += parseFloat($(this).attr('cost'));
            });
            $('#total').text('Total: Rs. ' + totalCost);
            if ($('.category-checkbox:checkbox:checked').length > 0) {
                $('.submit-btn').removeClass('disabled');
                $('.submit-btn').prop('disabled', false);
            }
            else {
                $('.submit-btn').prop('disabled', true);
                $('.submit-btn').addClass('disabled');
            }
        });
    });
});