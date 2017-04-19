$(document).ready(function(){
    $('#loading-container').show();
});
function indicador(data){
    if(data.status==='begin'){
       $('#loading').show();
    }
    if(data.status === 'complete'){
        $('#loading').hide();
    }
};
