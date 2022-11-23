class TemplatesCollection {

    static async from(templatesUrl, toolsUrl, languagesUrl, educationsUrl) {
        const templates = fetch(templatesUrl).then(response => response.text())
        const tools = fetch(toolsUrl).then(response => response.text())
        const languages = fetch(languagesUrl).then(response => response.text())
        const educations = Promise.resolve(`["NONE", "HIGHSCHOOL", "ASSOCIATES", "BACHELORS", "MASTERS"]`)
        return new TemplatesCollection(await templates, await tools, await languages, await educations)
    }

    #templates
    #tools
    #languages
    #educations
    constructor(templates, tools, languages, educations) {
        this.#templates = templates
        this.#tools = tools
        this.#languages = languages
        this.#educations = educations
    }

    get templates() {
        return JSON.parse(
            this.#templates
        )
    }

    get tools() {
        return JSON.parse(
            this.#tools
        )
    }

    get languages() {
        return JSON.parse(
            this.#languages
        )
    }

    get educations() {
        return JSON.parse(
            this.#educations
        )
    }
}

export default await TemplatesCollection.from(
    "/templates",
    "/tools",
    "/languages",
    "/educations" // unimplemented
);