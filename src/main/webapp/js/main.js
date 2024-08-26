$(document).ready(function () {
    let table;
    $.getJSON("exames", function (data) {
        table = $('#pacientes-table').DataTable({
            data: data,
            columns: [
                { 
                    "data": null, 
                    "render": function(data, type, row) {
                        return row.paciente ? row.paciente.patientID : '';
                    }
                },
                { 
                    "data": null, 
                    "render": function(data, type, row) {
                        return row.paciente ? row.paciente.nome : '';
                    }
                },
                { "data": "numero" },
                { "data": "tipoExame" },
                { "data": "modalidade" },
                { "data": "data" },
                { 
                    "data": "visualizacao", 
                    "render": function(data, type, row) {
                        return `<a href="${data}">Ler anamnese</a>`;
                    }
                }
            ],
            paging: true,
            pageLength: 10,
            lengthChange: false,
            searching: false, 
            ordering: true,
            order: [[0, "asc"]],
                  language: {
                paginate: {
                    previous: "Anterior",
                    next: "Próximo"
                },
                info: "Mostrando _START_ a _END_ de _TOTAL_ registros",
                infoEmpty: "Nenhum registro encontrado",
                infoFiltered: "(filtrado de _MAX_ registros no total)",
                lengthMenu: "Mostrar _MENU_ registros",
                search: "Buscar:",
                zeroRecords: "Nenhum registro encontrado"
            }
        });
    });

    // Filtro personalizado
    $('#search-btn').on('click', function() {
        const modalidade = $('#modalidade-select').val();
        const data = $('#data-input').val();
        const pesquisa = $('#search-input').val().toLowerCase();

        table.rows().every(function() {
            const rowData = this.data();
            let show = true;

            if (modalidade && rowData.modalidade !== modalidade) {
                show = false;
            }

            if (data && rowData.data !== data) {
                show = false;
            }

            if (pesquisa) {
                const pesquisaEncontrada = Object.values(rowData).some(value => 
                    value.toString().toLowerCase().includes(pesquisa)
                );
                if (!pesquisaEncontrada) {
                    show = false;
                }
            }

            this.nodes().to$().toggle(show);
        });

        table.draw();
    });
});
