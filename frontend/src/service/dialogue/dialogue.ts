import hyRequest from '..'

interface DialogueInputDto {
  question: string
  model: number
}

export function registry(dialogueInputDto: DialogueInputDto) {
  return hyRequest
    .instance({
      url: '/dialogue/answer',
      method: 'POST',
      data: dialogueInputDto,
    })
    .then((res) => {
      console.log(res)
    })
}
