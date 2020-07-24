{@org.greenscape.site.Page page}

import { LitElement, html } from "https://unpkg.com/lit-element/lit-element.js?module"

class Page extends LitElement {
	static get properties() {
	  return { name: String };
	}
	
	constructor() {
	  super();
	  this.name = '{page.name}';
	}
	
    render(){
        return html`
        <div>
        	<slot name="header"></slot>
        </div>
        <div>
        	<slot name="main"></slot>
        </div>
        <div>
        	<slot name="footer"></slot>
        </div>
        `
    }
}

customElements.define("g-page", Page)