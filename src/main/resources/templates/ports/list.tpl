layout 'main.tpl', title: 'Port list - vesseltracker.com',
	content: contents {
		div(class: 'content-body ports') {
			div(class: 'container coverage') {
				div(class: 'row') {
					div(class: 'col-12') {
						img(src: 'http://maps.vesseltracker.com/coverage/world_sat.png')
					}
				}
			}
			div(class: 'container') {
				div(class: 'row') {
					div(class: 'col-12') {
						table(class: 'table') {
							thead {
								tr {
									th('Country')
									th('Ports')
								}
							}
							tbody {
								countries.each { country ->
									tr {
										td {
											img(src: '//images.vesseltracker.com/images/flags/' + country.countryId + '.png', ' ' + country.name)
										}
										td(class: 'ports-column') {
											country.ports.each { port ->
												a(href: '/ports/details/' + port.name, port.name)
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}