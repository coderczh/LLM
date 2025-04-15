import type { DialogueInputDto } from '@/assets/common/common'
import hyRequest from '..'

const getAnswer = (dialogueInputDto: DialogueInputDto) => {
  return hyRequest.instance({
    url: '/dialogue/answer',
    method: 'POST',
    data: dialogueInputDto,
  })
}

export { getAnswer }
