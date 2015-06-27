yieldUnescaped '<!DOCTYPE html>'
html(lang:'en') {
	head {
		meta('http-equiv':'"Content-Type" content="text/html; charset=utf-8"')
		title(title)
		link(rel: 'stylesheet', media: 'screen', href: 'stylesheets/glyphicons.css')
		link(rel: 'stylesheet', media: 'screen', href: 'stylesheets/glyphicons-social.css')
		link(rel: 'stylesheet', media: 'screen', href: 'stylesheets/application.css')
	}
	body {
		div(class: 'wrapper') {
			div(class: 'header container-fluid') {
				div(class: 'topnav row') {
					div(class: 'col-12') {
						a(href: '/') {
							img(class: 'logo', src: '/images/vt-logo.png')
						}
						ul {
							li {
								a(href: '#') {
									span(class: 'glyphicons glyphicons-unlock') {}
									strong(' Log In')
								}
								yield ' | '
								a('Sign Up')
							}
							li {
								a(href: '#') {
									span(class: 'glyphicons glyphicons-bullhorn') {}
									yield ' Blog'
								}
							}
							li {
								a(href: '#') {
									span(class: 'glyphicons glyphicons-circle-question-mark') {}
									yield ' Support'
								}
							}
							li {
								a(href: '#') {
									span(class: 'glyphicons glyphicons-briefcase') {}
									yield ' Jobs'
								}
							}
						}
					}
				}
				div(class: 'navbar row') {
					div(class: 'col-9') {
						ul {
							li {
								a(href: '#', 'Products')
							}
							li {
								a(href: '/vessels', 'Vessels')
							}
							li {
								a(class: 'active', href: '/ports', 'Ports')
							}
							li {
								a(href: '#', 'Community')
							}
						}
					}
					div(class: 'col-3') {
						div(class: 'searchbox') {
							span(class: 'glyphicons glyphicons-search') {}
							input(type: 'search', placeholder: 'Search for port, ship, IMO, MMSI')
						}
					}
				}
			}
			
			div(class: 'content') { 
				content() 
			}
			
			div(class: 'footer') {
				div(class: 'top-footer') {
					div(class: 'container') {
						div(class: 'row') {
							div(class: 'col-3') {
								h5('Company')
								p {
									span('Vesseltracker is a ')
									a(href: 'http://www.genscape.com', 'Genscape')
									span(' company')
								}
								p {
									span('vesseltracker.com GmbH')
									br
									span('Mundsburger Damm 14')
									br
									span('22087 Hamburg, Germany')
								}
								p('Managing Directors: R. Paahsen, M. Leuschner')
							}
							div(class: 'col-3') {
								h5('Contact')
								p('Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore.')
								p {
									span(class: 'glyphicons glyphicons-phone-alt', '+49 (0) 40 97 07 86 - 10')
									br
									span(class: 'glyphicons glyphicons-fax', '+49 (0) 40 97 07 86 - 19')
								}
								p {
									span(class: 'glyphicons glyphicons-envelope')
									a(href: 'mailto:info@@vesseltracker.com', 'info@@vesseltracker.com')
								}
							}
							div(class: 'col-3') {
								h5('Antenna partnerships')
								p('Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore.')
								a(href: '#', 'Become an antenna partner')
							}
							div(class:'col-3') {
								h5('Jobs')
								p('Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore.')
								a(href: '#', 'View our open positions')
							}
						}
					}
				}
			}
			div(class: 'bottom-footer') {
				div(class: 'container') {
					div(class: 'row') {
						div(class: 'col-6') {
							p {
								span('&copy; 2015')
								a(href: 'https://www.vesseltracker.com', 'vesseltracker.com GmbH')
								span(' all rights reserved.')
							}
						} 
						div(class: 'col-6 social-icons') {
							a(href: '#', class: 'social social-twitter')
							a(href: '#', class: 'social social-facebook')
							a(href: '#', class: 'social social-youtube')
						}
					}
				}
			}
		}
	}
} 